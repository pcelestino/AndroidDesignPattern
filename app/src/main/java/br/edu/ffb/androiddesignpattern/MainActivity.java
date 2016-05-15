package br.edu.ffb.androiddesignpattern;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import br.edu.ffb.androiddesignpattern.classes.CalculadoraDeImposto;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIcms;
import br.edu.ffb.androiddesignpattern.classes.ImpostoIss;
import br.edu.ffb.androiddesignpattern.classes.Orcamento;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // coleta as referências visuais, Views
        final TextInputEditText etOrcamento = (TextInputEditText) findViewById(R.id.et_orcamento);
        Button btCalculaOrcamento = (Button) findViewById(R.id.bt_calcula_orcamento);
        final TextView tvOrcamentoCalculado = (TextView) findViewById(R.id.tv_orcamento_calculado);

        // para tentar minimizar os milhares de null exceptions do java
        if (etOrcamento == null) return;
        if (btCalculaOrcamento == null) return;
        if (tvOrcamentoCalculado == null) return;

        // ao clicar no botão Calcular é iniciado um evento
        btCalculaOrcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // converte a string para double
                String strValorDoOrcamento = etOrcamento.getText().toString();
                final double valorDoOrcamento = strValorDoOrcamento.isEmpty() ? 0 :
                        Double.parseDouble(strValorDoOrcamento);

                Orcamento orcamento = new Orcamento(valorDoOrcamento);

                CalculadoraDeImposto calculadoraDeImposto = new CalculadoraDeImposto();

                // realiza o cálculo dos impostos passando suas estratégias de imposto
                double icms = calculadoraDeImposto.RealizaCalculo(orcamento, new ImpostoIcms());
                double iss = calculadoraDeImposto.RealizaCalculo(orcamento, new ImpostoIss());

                String orcamentoCalculado = String.format(new Locale("pt", "BR"), "ICMS: %.2f" +
                        "\nISS: %.2f" +
                        "\nValor Final: %.2f", icms, iss, valorDoOrcamento - icms - iss);

                // exibe o resultado na view
                tvOrcamentoCalculado.setText(orcamentoCalculado);
            }
        });
    }
}
