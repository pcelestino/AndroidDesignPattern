package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.Interfaces.IDesconto;

/**
 * Desconto de 7% para item com um valor superior a 500
 */
public class DescontoPorMaisDeQuinhentosReais implements IDesconto {

    private IDesconto mProximo;

    @Override
    public double Desconta(Orcamento orcamento) {

        if (orcamento.getValor() > 500) {
            return orcamento.getValor() * 0.07;
        }

        return mProximo.Desconta(orcamento);
    }

    @Override
    public void SetProximo(IDesconto proximo) {
        mProximo = proximo;
    }
}
