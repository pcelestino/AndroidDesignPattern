package br.edu.ffb.androiddesignpattern.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Item que será adicionado na lista de itens do orçamento
 */
public class Item implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mNome);
        dest.writeDouble(this.mValor);
    }

    protected Item(Parcel in) {
        this.mNome = in.readString();
        this.mValor = in.readDouble();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
