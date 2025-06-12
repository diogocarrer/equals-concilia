package com.equals.concilia.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Header {
    private String codigoRegistro;
    private String numeroEstabelecimento;
    private LocalDate dataGeracao;
    private LocalDate periodoInicial;
    private LocalDate periodoFinal;
    private String versaoLayout;

    public static Header fromLine(String linha) {
        DateTimeFormatter fmt = DateTimeFormatter.BASIC_ISO_DATE;
        Header h = new Header();
        h.setCodigoRegistro(linha.substring(0, 1));
        h.setNumeroEstabelecimento(linha.substring(1, 11).trim());
        h.setDataGeracao(LocalDate.parse(linha.substring(11, 19), fmt));
        h.setPeriodoInicial(LocalDate.parse(linha.substring(19, 27), fmt));
        h.setPeriodoFinal(LocalDate.parse(linha.substring(27, 35), fmt));
        h.setVersaoLayout(linha.substring(35, 37).trim());
        return h;
    }

    public String getNumeroEstabelecimento() {
        return numeroEstabelecimento;
    }

    public void setNumeroEstabelecimento(String numeroEstabelecimento) {
        this.numeroEstabelecimento = numeroEstabelecimento;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public LocalDate getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(LocalDate periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public LocalDate getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(LocalDate periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public String getVersaoLayout() {
        return versaoLayout;
    }

    public void setVersaoLayout(String versaoLayout) {
        this.versaoLayout = versaoLayout;
    }
}

