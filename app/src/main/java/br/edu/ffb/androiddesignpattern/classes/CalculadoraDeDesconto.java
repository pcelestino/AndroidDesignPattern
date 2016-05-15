package br.edu.ffb.androiddesignpattern.classes;

/**
 * Realiza o cálculo do desconto
 */
public class CalculadoraDeDesconto {

    public double Calcula(Orcamento orcamento) {
        DescontoPorMaisDeCincoItens descontoPorMaisDeCincoItens = new DescontoPorMaisDeCincoItens();
        DescontoPorMaisDeQuinhentosReais descontoPorMaisDeQuinhentosReais = new DescontoPorMaisDeQuinhentosReais();
        SemDesconto semDesconto = new SemDesconto();

        descontoPorMaisDeCincoItens.SetProximo(descontoPorMaisDeQuinhentosReais);
        descontoPorMaisDeQuinhentosReais.SetProximo(semDesconto);

        return descontoPorMaisDeCincoItens.Desconta(orcamento);
    }
}
