package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.classes.abstratas.TemplateDeImpostoCondicional;

/**
 * ICPP = 7% caso o orçamento seja superior ou igual a 500 e 5% caso contrário
 */
public class ImpostoIcpp extends TemplateDeImpostoCondicional {

    @Override
    protected boolean DeveUsarMaximaTaxacao(Orcamento orcamento) {

        return orcamento.getValor() >= 500;
    }

    @Override
    protected double MaximaTaxacao(Orcamento orcamento) {

        return orcamento.getValor() * 0.07;
    }

    @Override
    protected double MinimaTaxacao(Orcamento orcamento) {

        return orcamento.getValor() * 0.05;
    }
}
