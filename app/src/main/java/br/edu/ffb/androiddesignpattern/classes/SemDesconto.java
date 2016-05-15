package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.Interfaces.IDesconto;

/**
 * Para evitar uma referência nula caso nenhuma das condições dos descontos sejam satisfeitas
 */
public class SemDesconto implements IDesconto {
    @Override
    public double Desconta(Orcamento orcamento) {
        return 0;
    }

    @Override
    public void SetProximo(IDesconto proximo) {
    }
}
