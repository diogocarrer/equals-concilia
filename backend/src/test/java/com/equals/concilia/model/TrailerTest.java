package com.equals.concilia.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrailerTest {

    @Test
    public void testFromLineSimples() {
        String linha = "900000000008"; // linha real e mínima

        Trailer trailer = Trailer.fromLine(linha);

        assertNotNull(trailer);
        assertEquals("9", trailer.getCodigoRegistro());
        assertEquals(8L, trailer.getTotalRegistros());
        assertEquals("", trailer.getReservado());
    }
}
