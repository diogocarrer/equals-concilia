package com.equals.concilia.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoTest {

    @Test
    void deveReconhecerTransacaoComBandeiraELO() {
        String linha =
                "11234567891201809252018092513183401016831815410203040506070809010203040506070000002              00000000001010000000000100M1 1 012018102500000000000000000000000000000000000010100000000000000000000000001000000000000000000000000000000000000000000000000010001  03ELO                            ME1201                                1003413100    2345678901589082115721";

        Transacao tx = Transacao.fromLine(linha);

        assertEquals("ELO", tx.getInstituicaoBandeira().trim());
    }
}
