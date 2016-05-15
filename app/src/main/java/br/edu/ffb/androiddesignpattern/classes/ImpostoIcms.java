package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.Interfaces.IImposto;

/**
 * ICMS = 10% do Orçamento
 */
public class ImpostoIcms implements IImposto {

    @Override
    public double Calcula(Orcamento orcamento) {
        return orcamento.getValor() * 0.1;
    }
}
