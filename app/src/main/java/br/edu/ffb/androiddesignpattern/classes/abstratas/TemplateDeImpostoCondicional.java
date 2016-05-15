package br.edu.ffb.androiddesignpattern.classes.abstratas;

import br.edu.ffb.androiddesignpattern.Interfaces.IImposto;
import br.edu.ffb.androiddesignpattern.classes.Orcamento;

/**
 * Template que irá completar as lógicas repetidas em ImpostoIkcv e ImpostoIcpp
 */
public abstract class TemplateDeImpostoCondicional implements IImposto {

    public double Calcula(Orcamento orcamento) {
        return DeveUsarMaximaTaxacao(orcamento) ? MaximaTaxacao(orcamento) : MinimaTaxacao(orcamento);
    }

    protected abstract boolean DeveUsarMaximaTaxacao(Orcamento orcamento);

    protected abstract double MaximaTaxacao(Orcamento orcamento);

    protected abstract double MinimaTaxacao(Orcamento orcamento);
}
