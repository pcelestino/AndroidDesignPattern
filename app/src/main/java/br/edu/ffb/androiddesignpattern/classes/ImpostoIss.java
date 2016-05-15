package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.Interfaces.IImposto;

/**
 * ISS = 6% do valor do orçamento
 */
public class ImpostoIss implements IImposto {

    @Override
    public double Calcula(Orcamento orcamento) {
        return orcamento.getValor() * 0.06;
    }
}
