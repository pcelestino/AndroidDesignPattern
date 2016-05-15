package br.edu.ffb.androiddesignpattern.classes;

import br.edu.ffb.androiddesignpattern.Interfaces.IImposto;

/**
 * Realiza o cálculo do imposto
 */
public class CalculadorDeImpostos {

    public double RealizaCalculo(Orcamento orcamento, IImposto imposto) {
        return imposto.Calcula(orcamento);
    }
}
