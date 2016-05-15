package br.edu.ffb.androiddesignpattern.classes;

import android.content.Context;
import android.widget.Toast;

import br.edu.ffb.androiddesignpattern.Interfaces.IAcaoAposGerarNota;

/**
 * Evento que salva a Nota Fiscal no banco
 */
public class SalvaNoBanco implements IAcaoAposGerarNota {

    @Override
    public void Executa(Context context, NotaFiscal notaFiscal) {

        Toast.makeText(context, "Nota Fiscal salva no banco com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
