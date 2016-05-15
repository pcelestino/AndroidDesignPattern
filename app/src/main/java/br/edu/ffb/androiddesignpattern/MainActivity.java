package br.edu.ffb.androiddesignpattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.edu.ffb.androiddesignpattern.classes.CalculadoraDeDesconto;
import br.edu.ffb.androiddesignpattern.classes.CalculadoraDeImposto;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIcms;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIss;
import br.edu.ffb.androiddesignpattern.classes.Item;
import br.edu.ffb.androiddesignpattern.classes.Orcamento;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Orcamento orcamento = new Orcamento();
        final CalculadoraDeImposto calculadoraDeImposto = new CalculadoraDeImposto();
        final CalculadoraDeDesconto calculadoraDeDesconto = new CalculadoraDeDesconto();
        final Locale localePtBr = new Locale("pt", "BR");

        final ListView lvItens = (ListView) findViewById(R.id.lv_itens);
        FloatingActionButton fbAdicionaItem = (FloatingActionButton) findViewById(R.id.fb_adiciona_item);
        final TextView tvIcmsValor = (TextView) findViewById(R.id.icms_valor);
        final TextView tvIssValor = (TextView) findViewById(R.id.iss_valor);
        final TextView tvDescontoValor = (TextView) findViewById(R.id.desconto_valor);
        final TextView tvValorFinal = (TextView) findViewById(R.id.valor_final);

        if (lvItens == null) return;
        if (fbAdicionaItem == null) return;
        if (tvIcmsValor == null) return;
        if (tvIssValor == null) return;
        if (tvDescontoValor == null) return;
        if (tvValorFinal == null) return;

        // criando um adapter simples para a lista
        final List<String[]> listaDeItens = new ArrayList<>();

        @SuppressWarnings("unchecked") final
        ArrayAdapter<String[]> stringArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, listaDeItens) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String[] entrada = listaDeItens.get(position);
                TextView titulo = (TextView) view.findViewById(android.R.id.text1);
                TextView subtitulo = (TextView) view.findViewById(android.R.id.text2);
                titulo.setText(entrada[0]);

                double valorDoItem = entrada[1].isEmpty() ? 0 :
                        Double.parseDouble(entrada[1]);

                subtitulo.setText(String.format(localePtBr, "R$ %.2f", valorDoItem));

                return view;
            }
        };

        lvItens.setAdapter(stringArrayAdapter);

        fbAdicionaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                @SuppressLint("InflateParams")
                final View dialogView = inflater.inflate(R.layout.dialog_adiciona_item, null);

                final TextInputEditText etItem = (TextInputEditText) dialogView.findViewById(R.id.et_item);
                final TextInputEditText etValorItem = (TextInputEditText) dialogView.findViewById(R.id.et_valor_item);

                // exibe o teclado
                final InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                final AlertDialog alertDialog = dialogBuilder
                        .setView(dialogView)
                        .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String strNomeDoItem = etItem.getText().toString();
                                String strValorDoItem = etValorItem.getText().toString();

                                listaDeItens.add(new String[]{strNomeDoItem, strValorDoItem});
                                stringArrayAdapter.notifyDataSetChanged();

                                double valorDoItem = strValorDoItem.isEmpty() ? 0 :
                                        Double.parseDouble(strValorDoItem);

                                orcamento.adicionaItem(new Item(strNomeDoItem, valorDoItem));

                                double icms = calculadoraDeImposto.RealizaCalculo(orcamento, new ImpostoIcms());
                                double iss = calculadoraDeImposto.RealizaCalculo(orcamento, new ImpostoIss());
                                double desconto = calculadoraDeDesconto.Calcula(orcamento);

                                String strIcmsValor = String.format(localePtBr, "R$ %.2f", icms);
                                String strIssValor = String.format(localePtBr, "R$ %.2f", iss);

                                tvIcmsValor.setText(strIcmsValor);
                                tvIssValor.setText(strIssValor);
                                tvDescontoValor.setText(String.format(localePtBr, "R$ %.2f", desconto));
                                tvValorFinal.setText(String.format(localePtBr, "R$ %.2f", (orcamento.getValor() + icms + iss) - desconto));

                                // esconde o teclado
                                imm.toggleSoftInput(0, 0);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // esconde o teclado
                                imm.toggleSoftInput(0, 0);
                            }
                        })
                        .create();

                // adiciona um evento para fechar o dialog ao clicar no botão done do teclado
                etValorItem.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).callOnClick();
                            return true;
                        }
                        return false;
                    }
                });

                // exibe o dialog
                alertDialog.show();
            }
        });
    }
}
