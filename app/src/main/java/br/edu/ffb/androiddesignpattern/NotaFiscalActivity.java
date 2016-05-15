package br.edu.ffb.androiddesignpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.edu.ffb.androiddesignpattern.classes.Item;
import br.edu.ffb.androiddesignpattern.classes.NotaFiscal;

public class NotaFiscalActivity extends AppCompatActivity {

    private Locale mLocalePtBr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_fiscal);

        mLocalePtBr = new Locale("pt", "BR");

        NotaFiscal notaFiscal = getIntent().getParcelableExtra(NotaFiscal.NAME);

        TextView tvRazaoSocial = (TextView) findViewById(R.id.tv_nota_fiscal_razao_social_valor);
        TextView tvCnpj = (TextView) findViewById(R.id.tv_nota_fiscal_cnpj_valor);
        TextView tvDataAtual = (TextView) findViewById(R.id.tv_nota_fiscal_data_atual);
        ListView lvListaDeItens = (ListView) findViewById(R.id.lv_nota_fiscal_lista_itens);
        TextView tvTotalImpostos = (TextView) findViewById(R.id.tv_nota_fiscal_total_impostos_valor);
        TextView tvTotalDescontos = (TextView) findViewById(R.id.tv_nota_fiscal_total_descontos_valor);
        TextView tvValorTotal = (TextView) findViewById(R.id.tv_nota_fiscal_valor_total_valor);
        TextView tvObservacoes = (TextView) findViewById(R.id.tv_nota_fiscal_observacoes_valor);

        if (notaFiscal == null) return;
        if (tvRazaoSocial == null) return;
        if (tvCnpj == null) return;
        if (tvDataAtual == null) return;
        if (lvListaDeItens == null) return;
        if (tvTotalImpostos == null) return;
        if (tvTotalDescontos == null) return;
        if (tvValorTotal == null) return;
        if (tvObservacoes == null) return;

        tvRazaoSocial.setText(notaFiscal.getRazaoSocial());
        tvCnpj.setText(notaFiscal.getCnpj());
        tvDataAtual.setText(DataFormatada(notaFiscal.getDataDeEmissao()));
        tvTotalImpostos.setText(ValorFormatado(notaFiscal.getImpostos()));
        tvTotalDescontos.setText(ValorFormatado(notaFiscal.getDescontos()));
        tvValorTotal.setText(ValorFormatado(notaFiscal.getValorTotal()));
        tvObservacoes.setText(notaFiscal.getObservacoes());

        final List<Item> listaDeItens = notaFiscal.getItensDaNota();

        @SuppressWarnings("unchecked") final
        ArrayAdapter<List<Item>> listaDeItensAdapter = new ArrayAdapter(this, R.layout.listview_row_nota_fiscal, R.id.tv_row_item_nome, listaDeItens) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Item entrada = listaDeItens.get(position);
                TextView titulo = (TextView) view.findViewById(R.id.tv_row_item_nome);
                TextView subtitulo = (TextView) view.findViewById(R.id.tv_row_item_valor);
                titulo.setText(entrada.getNome());
                subtitulo.setText(ValorFormatado(entrada.getValor()));

                return view;
            }
        };

        lvListaDeItens.setAdapter(listaDeItensAdapter);
    }

    private String DataFormatada(Date dataAtual) {

        return new SimpleDateFormat("dd/MM/yyyy HH:mm", mLocalePtBr).format(dataAtual);
    }

    private String ValorFormatado(double valor) {

        return String.format(mLocalePtBr, "R$ %.2f", valor);
    }
}
