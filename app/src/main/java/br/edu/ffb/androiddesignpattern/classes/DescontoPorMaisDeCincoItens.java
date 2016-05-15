package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.Interfaces.IDesconto;

/**
 * Desconto de 10% para mais de cinco itens cadastrados
 */
public class DescontoPorMaisDeCincoItens implements IDesconto {

    private IDesconto mProximo;

    @Override
    public double Desconta(Orcamento orcamento) {

        if (orcamento.getItens().size() > 4) {
            return orcamento.getValor() * 0.1;
        }

        return mProximo.Desconta(orcamento);
    }

    @Override
    public void SetProximo(IDesconto proximo) {
        mProximo = proximo;
    }
}
