package com.equals.concilia.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrailerTest {

    @Test
    void deveParsearQuantidadeRegistros() {
        String linha = "900000000005" + " ".repeat(518);
        Trailer trailer = Trailer.fromLine(linha);

        assertEquals("9", trailer.getCodigoRegistro());
        assertEquals(5L, trailer.getTotalRegistros());
    }
}
