package br.edu.ffb.androiddesignpattern.Interfaces;

import br.edu.ffb.androiddesignpattern.classes.Orcamento;

/**
 * Interface necessária para a implementação das estratégias de impostos
 */
public interface IImposto {
    double Calcula(Orcamento orcamento);
}
