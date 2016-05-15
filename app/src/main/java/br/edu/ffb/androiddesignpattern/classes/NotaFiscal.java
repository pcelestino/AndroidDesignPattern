package br.edu.ffb.androiddesignpattern.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Nota Fiscal do cliente
 */
public class NotaFiscal implements Parcelable {

    public static final String NAME = NotaFiscal.class.getSimpleName();

    private String mRazaoSocial;
    private String mCnpj;
    private String mObservacoes;
    private double mValorBruto;
    private double mImpostos;
    private double mDescontos;
    private Date mDataDeEmissao;
    private List<Item> mItensDaNota;

    public NotaFiscal(String razaoSocial, String cnpj, String observacoes, double valorBruto, double impostos, double descontos, Date dataDeEmissao, List<Item> itensDaNota) {

        mRazaoSocial = razaoSocial;
        mCnpj = cnpj;
        mObservacoes = observacoes;
        mValorBruto = valorBruto;
        mImpostos = impostos;
        mDescontos = descontos;
        mDataDeEmissao = dataDeEmissao;
        mItensDaNota = itensDaNota;
    }

    public String getRazaoSocial() {
        return mRazaoSocial;
    }

    public String getCnpj() {
        return mCnpj;
    }

    public String getObservacoes() {
        return mObservacoes;
    }

    public double getImpostos() {
        return mImpostos;
    }

    public double getDescontos() {
        return mDescontos;
    }

    public Date getDataDeEmissao() {
        return mDataDeEmissao;
    }

    public List<Item> getItensDaNota() {
        return mItensDaNota;
    }

    public double getValorTotal() {
        return mValorBruto + mImpostos - mDescontos;
    }

    @Override
    public String toString() {

        return "NotaFiscal{" +
                "mRazaoSocial='" + mRazaoSocial + '\'' +
                ", mCnpj='" + mCnpj + '\'' +
                ", mObservacoes='" + mObservacoes + '\'' +
                ", mValorBruto=" + mValorBruto +
                ", mImpostos=" + mImpostos +
                ", mDescontos=" + mDescontos +
                ", mDataDeEmissao=" + mDataDeEmissao +
                ", mItensDaNota=" + mItensDaNota +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mRazaoSocial);
        dest.writeString(this.mCnpj);
        dest.writeString(this.mObservacoes);
        dest.writeDouble(this.mValorBruto);
        dest.writeDouble(this.mImpostos);
        dest.writeDouble(this.mDescontos);
        dest.writeLong(this.mDataDeEmissao != null ? this.mDataDeEmissao.getTime() : -1);
        dest.writeList(this.mItensDaNota);
    }

    protected NotaFiscal(Parcel in) {
        this.mRazaoSocial = in.readString();
        this.mCnpj = in.readString();
        this.mObservacoes = in.readString();
        this.mValorBruto = in.readDouble();
        this.mImpostos = in.readDouble();
        this.mDescontos = in.readDouble();
        long tmpMDataDeEmissao = in.readLong();
        this.mDataDeEmissao = tmpMDataDeEmissao == -1 ? null : new Date(tmpMDataDeEmissao);
        this.mItensDaNota = new ArrayList<>();
        in.readList(this.mItensDaNota, Item.class.getClassLoader());
    }

    public static final Parcelable.Creator<NotaFiscal> CREATOR = new Parcelable.Creator<NotaFiscal>() {
        @Override
        public NotaFiscal createFromParcel(Parcel source) {
            return new NotaFiscal(source);
        }

        @Override
        public NotaFiscal[] newArray(int size) {
            return new NotaFiscal[size];
        }
    };
}
