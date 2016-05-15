package br.edu.ffb.androiddesignpattern.classes;

/**
 * Item que será adicionado na lista de itens do orçamento
 */
public class Item {
    private final String mNome;
    private final double mValor;

    public Item(String nome, double valor) {
        mNome = nome;
        mValor = valor;
    }

    public String getNome() {
        return mNome;
    }

    public double getValor() {
        return mValor;
    }
}
