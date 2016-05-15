package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.classes.abstratas.TemplateDeImpostoCondicional;

/**
 * IKCV = 10% caso o orçamento seja maior que 500 e tenha algum item com valor superior a 500
 * e 6% caso contrário
 */
public class ImpostoIkcv extends TemplateDeImpostoCondicional {

    @Override
    protected boolean DeveUsarMaximaTaxacao(Orcamento orcamento) {

        return orcamento.getValor() > 500 && TemItemMaiorQue500ReaisNo(orcamento);
    }

    @Override
    protected double MaximaTaxacao(Orcamento orcamento) {

        return orcamento.getValor() * 0.1;
    }

    @Override
    protected double MinimaTaxacao(Orcamento orcamento) {

        return orcamento.getValor() * 0.06;
    }

    private static boolean TemItemMaiorQue500ReaisNo(Orcamento orcamento) {

        for (Item item : orcamento.getItens()) {

            if (item.getValor() > 500) return true;
        }

        return false;
    }
}
