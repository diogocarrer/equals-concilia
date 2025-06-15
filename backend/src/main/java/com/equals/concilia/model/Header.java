package com.equals.concilia.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Header {
    private String codigoRegistro;
    private String numeroEstabelecimento;
    private LocalDate dataGeracao;
    private LocalDate periodoInicial;
    private LocalDate periodoFinal;
    private String sequencia;
    private String empresaAdquirente;
    private String tipoExtrato;
    private String filler;
    private String versaoLayout;
    private String versaoRelease;

    public static Header fromLine(String linha) {
        DateTimeFormatter fmt = DateTimeFormatter.BASIC_ISO_DATE;
        Header h = new Header();
        h.setCodigoRegistro(linha.substring(0, 1));
        h.setNumeroEstabelecimento(linha.substring(1, 11).trim());
        h.setDataGeracao(LocalDate.parse(linha.substring(11, 19), fmt));
        h.setPeriodoInicial(LocalDate.parse(linha.substring(19, 27), fmt));
        h.setPeriodoFinal(LocalDate.parse(linha.substring(27, 35), fmt));
        h.setSequencia(linha.substring(35, 42).trim());
        h.setEmpresaAdquirente(linha.substring(42, 47).trim());
        h.setTipoExtrato(linha.substring(47, 49).trim());
        h.setFiller(linha.substring(49, 70).trim());
        h.setVersaoLayout(linha.substring(70, 73).trim());
        h.setVersaoRelease(linha.substring(73, 78).trim());
        return h;
    }

    // Getters e setters

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getNumeroEstabelecimento() {
        return numeroEstabelecimento;
    }

    public void setNumeroEstabelecimento(String numeroEstabelecimento) {
        this.numeroEstabelecimento = numeroEstabelecimento;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public LocalDate getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(LocalDate periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public LocalDate getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(LocalDate periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getEmpresaAdquirente() {
        return empresaAdquirente;
    }

    public void setEmpresaAdquirente(String empresaAdquirente) {
        this.empresaAdquirente = empresaAdquirente;
    }

    public String getTipoExtrato() {
        return tipoExtrato;
    }

    public void setTipoExtrato(String tipoExtrato) {
        this.tipoExtrato = tipoExtrato;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getVersaoLayout() {
        return versaoLayout;
    }

    public void setVersaoLayout(String versaoLayout) {
        this.versaoLayout = versaoLayout;
    }

    public String getVersaoRelease() {
        return versaoRelease;
    }

    public void setVersaoRelease(String versaoRelease) {
        this.versaoRelease = versaoRelease;
    }
}
