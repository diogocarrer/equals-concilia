package com.equals.concilia.model;

import jakarta.persistence.*;

@Entity
public class Trailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoRegistro;
    private long totalRegistros;
    private String reservado;

    public static Trailer fromLine(String linha) {
        Trailer t = new Trailer();

        t.setCodigoRegistro(linha.substring(0, 1));
        String rawTotal = linha.substring(1, 12).trim();
        t.setTotalRegistros(Long.parseLong(rawTotal));
        t.setReservado(linha.length() > 12 ? linha.substring(12) : "");

        return t;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public long getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(long totalRegistros) {
        this.totalRegistros = totalRegistros;
    }
}
