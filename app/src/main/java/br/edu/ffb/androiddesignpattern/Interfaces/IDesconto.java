package br.edu.ffb.androiddesignpattern.Interfaces;

import br.edu.ffb.androiddesignpattern.classes.Orcamento;

/**
 * Interface necessária para gerar a corrente de responsabilidades
 */
public interface IDesconto {

    double Desconta(Orcamento orcamento);

    void SetProximo(IDesconto proximo);
}
