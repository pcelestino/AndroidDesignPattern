package br.edu.ffb.androiddesignpattern.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe especializada em gerar Notas Fiscais
 */
public class NotaFiscalBuilder {

    private String _razaoSocial;
    private String _cnpj;
    private String _observacoes;
    private double _valorBruto;
    private double _impostos;
    private double _descontos;
    private Date _dataDeEmissao;
    private List<Item> _itensDaNota;

    public NotaFiscalBuilder()
    {
        _itensDaNota = new ArrayList<>();
    }

    public NotaFiscalBuilder ParaEmpresa(String razaoSocial)
    {
        _razaoSocial = razaoSocial;
        return this;
    }

    public NotaFiscalBuilder ComCnpj(String cnpj)
    {
        _cnpj = cnpj;
        return this;
    }

    public NotaFiscalBuilder ComObservacoes(String observacoes)
    {
        _observacoes = observacoes;
        return this;
    }

    public NotaFiscalBuilder ComOrcamento(Orcamento orcamento)
    {
        _valorBruto = orcamento.getValor();
        _itensDaNota = orcamento.getItens();
        return this;
    }

    public NotaFiscalBuilder ComImposto(double impostos)
    {
        _impostos += impostos;
        return this;
    }

    public NotaFiscalBuilder ComDesconto(double descontos)
    {
        _descontos += descontos;
        return this;
    }

    public NotaFiscalBuilder NaDataAtual()
    {
        _dataDeEmissao = new Date();
        return this;
    }

    public NotaFiscal Constroi()
    {
        return new NotaFiscal(_razaoSocial, _cnpj, _observacoes, _valorBruto, _impostos, _descontos, _dataDeEmissao, _itensDaNota);
    }
}
