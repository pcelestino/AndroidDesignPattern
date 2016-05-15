package br.edu.ffb.androiddesignpattern.classes;

import android.content.Context;
import android.widget.Toast;

import br.edu.ffb.androiddesignpattern.Interfaces.IAcaoAposGerarNota;

/**
 * Evento que envia a Nota Fiscal por sms
 */
public class EnviadorDeSms implements IAcaoAposGerarNota {

    @Override
    public void Executa(Context context, NotaFiscal notaFiscal) {

        Toast.makeText(context, "Nota Fiscal enviada por sms com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
