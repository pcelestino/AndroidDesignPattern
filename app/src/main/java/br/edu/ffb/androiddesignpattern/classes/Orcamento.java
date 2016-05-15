package br.edu.ffb.androiddesignpattern.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Orçamento do cliente
 */
public class Orcamento {

    private double mValor;
    private final List<Item> mItens;

    public Orcamento() {
        mItens = new ArrayList<>();
    }

    public Orcamento(double valor) {
        mValor = valor;
        mItens = new ArrayList<>();
    }

    public double getValor() {
        return mValor;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(mItens);
    }

    public void adicionaItem(Item item) {
        mItens.add(item);
        mValor += item.getValor();
    }
}
