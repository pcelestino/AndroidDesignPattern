package br.edu.ffb.androiddesignpattern.Interfaces;

import android.content.Context;

import br.edu.ffb.androiddesignpattern.classes.NotaFiscal;

/**
 * Interface necessária para facilitar o registro dos eventos e ficar observando
 */
public interface IAcaoAposGerarNota {

    void Executa(Context context, NotaFiscal notaFiscal);
}
