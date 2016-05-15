package br.edu.ffb.androiddesignpattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import br.edu.ffb.androiddesignpattern.classes.EnviadorDeEmail;
import br.edu.ffb.androiddesignpattern.classes.EnviadorDeSms;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIcms;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIcpp;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIkcv;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIss;
import br.edu.ffb.androiddesignpattern.classes.Item;
import br.edu.ffb.androiddesignpattern.classes.NotaFiscal;
import br.edu.ffb.androiddesignpattern.classes.NotaFiscalBuilder;
import br.edu.ffb.androiddesignpattern.classes.Orcamento;
import br.edu.ffb.androiddesignpattern.classes.SalvaNoBanco;

public class PrincipalActivity extends AppCompatActivity {

    private Locale mLocalePtBr;
    private Orcamento mOrcamento;
    private double mIcms;
    private double mIss;
    private double mIkcv;
    private double mIcpp;
    private double mDesconto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mLocalePtBr = new Locale("pt", "BR");

        mOrcamento = new Orcamento();
        final CalculadoraDeImposto calculadoraDeImposto = new CalculadoraDeImposto();
        final CalculadoraDeDesconto calculadoraDeDesconto = new CalculadoraDeDesconto();

        FloatingActionButton fbAdicionaItem = (FloatingActionButton) findViewById(R.id.fb_adiciona_item);
        final ListView lvItens = (ListView) findViewById(R.id.lv_itens);
        final TextView tvIcmsValor = (TextView) findViewById(R.id.icms_valor);
        final TextView tvIssValor = (TextView) findViewById(R.id.iss_valor);
        final TextView tvIkcvValor = (TextView) findViewById(R.id.ikcv_valor);
        final TextView tvIcppValor = (TextView) findViewById(R.id.icpp_valor);
        final TextView tvDescontoValor = (TextView) findViewById(R.id.desconto_valor);
        final TextView tvValorFinal = (TextView) findViewById(R.id.valor_final);

        if (fbAdicionaItem == null) return;
        if (lvItens == null) return;
        if (tvIcmsValor == null) return;
        if (tvIssValor == null) return;
        if (tvIkcvValor == null) return;
        if (tvIcppValor == null) return;
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

                subtitulo.setText(ValorFormatado(valorDoItem));

                return view;
            }
        };

        lvItens.setAdapter(stringArrayAdapter);

        fbAdicionaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PrincipalActivity.this);
                LayoutInflater inflater = PrincipalActivity.this.getLayoutInflater();

                @SuppressLint("InflateParams")
                final View dialogView = inflater.inflate(R.layout.dialog_adiciona_item, null);

                final TextInputEditText etItem = (TextInputEditText) dialogView.findViewById(R.id.et_item);
                final TextInputEditText etValorItem = (TextInputEditText) dialogView.findViewById(R.id.et_valor_item);

                // exibe o teclado
                final InputMethodManager imm = (InputMethodManager) PrincipalActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                final AlertDialog alertDialog = dialogBuilder
                        .setView(dialogView)
                        .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String strNomeDoItem = etItem.getText().toString();
                                String strValorDoItem = etValorItem.getText().toString();

                                listaDeItens.add(new String[]{strNomeDoItem, strValorDoItem});
                                stringArrayAdapter.notifyDataSetChanged();

                                double valorDoItem = strValorDoItem.isEmpty() ? 0 :
                                        Double.parseDouble(strValorDoItem);

                                mOrcamento.adicionaItem(new Item(strNomeDoItem, valorDoItem));

                                mIcms = calculadoraDeImposto.RealizaCalculo(mOrcamento, new ImpostoIcms());
                                mIss = calculadoraDeImposto.RealizaCalculo(mOrcamento, new ImpostoIss());
                                mIkcv = calculadoraDeImposto.RealizaCalculo(mOrcamento, new ImpostoIkcv());
                                mIcpp = calculadoraDeImposto.RealizaCalculo(mOrcamento, new ImpostoIcpp());
                                mDesconto = calculadoraDeDesconto.Calcula(mOrcamento);

                                String strIcmsValor = ValorFormatado(mIcms);
                                String strIssValor = ValorFormatado(mIss);
                                String strIkcvValor = ValorFormatado(mIkcv);
                                String strIcppValor = ValorFormatado(mIcpp);
                                String strDesconto = ValorFormatado(mDesconto);
                                String strValorFinal = ValorFormatado((mOrcamento.getValor() + mIcms + mIss + mIkcv + mIcpp) - mDesconto);

                                tvIcmsValor.setText(strIcmsValor);
                                tvIssValor.setText(strIssValor);
                                tvIkcvValor.setText(strIkcvValor);
                                tvIcppValor.setText(strIcppValor);
                                tvDescontoValor.setText(strDesconto);
                                tvValorFinal.setText(strValorFinal);

                                // esconde o teclado
                                imm.toggleSoftInput(0, 0);
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_nota_fiscal:

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PrincipalActivity.this);
                LayoutInflater inflater = PrincipalActivity.this.getLayoutInflater();

                @SuppressLint("InflateParams")
                final View dialogView = inflater.inflate(R.layout.dialog_cria_nota_fiscal, null);

                final TextInputEditText etRazaoSocial = (TextInputEditText) dialogView.findViewById(R.id.et_razao_social);
                final TextInputEditText etCnpj = (TextInputEditText) dialogView.findViewById(R.id.et_cnpj);
                final TextInputEditText etObservacoes = (TextInputEditText) dialogView.findViewById(R.id.et_observacoes);

                // exibe o teclado
                final InputMethodManager imm = (InputMethodManager) PrincipalActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                final AlertDialog alertDialog = dialogBuilder
                        .setView(dialogView)
                        .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String strRazaoSocial = etRazaoSocial.getText().toString();
                                String strCnpj = etCnpj.getText().toString();
                                String strObservacoes = etObservacoes.getText().toString();

                                NotaFiscal notaFiscal = new NotaFiscalBuilder()
                                        .ParaEmpresa(strRazaoSocial)
                                        .ComCnpj(strCnpj)
                                        .ComObservacoes(strObservacoes)
                                        .ComOrcamento(mOrcamento)
                                        .ComImposto(mIcms)
                                        .ComImposto(mIss)
                                        .ComImposto(mIkcv)
                                        .ComImposto(mIcpp)
                                        .ComDesconto(mDesconto)
                                        .NaDataAtual()
                                        // **** Padrão Observer (Registrando Eventos)****
                                        .AdicionaAcao(new EnviadorDeEmail())
                                        .AdicionaAcao(new EnviadorDeSms())
                                        .AdicionaAcao(new SalvaNoBanco())
                                        // **********************************************
                                        .Constroi(PrincipalActivity.this);

                                Intent irParaNotaFiscal = new Intent(PrincipalActivity.this, NotaFiscalActivity.class);
                                irParaNotaFiscal.putExtra(NotaFiscal.NAME, notaFiscal);
                                startActivity(irParaNotaFiscal);

                                // esconde o teclado
                                imm.toggleSoftInput(0, 0);
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // esconde o teclado
                                imm.toggleSoftInput(0, 0);
                            }
                        })
                        .create();

                // exibe o dialog
                alertDialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String ValorFormatado(double valor) {
        return String.format(mLocalePtBr, "R$ %.2f", valor);
    }
}
