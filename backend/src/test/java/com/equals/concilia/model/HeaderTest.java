package com.equals.concilia.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class HeaderTest {

    @Test
    public void testFromLineCompleto() {
        String linha = "012345678912018112920180925201809250017799FICTI01A                    002.002c";

        Header header = Header.fromLine(linha);

        assertNotNull(header);
        assertEquals("0", header.getCodigoRegistro());
        assertEquals("1234567891", header.getNumeroEstabelecimento());
        assertEquals(LocalDate.of(2018, 11, 29), header.getDataGeracao());
        assertEquals(LocalDate.of(2018, 9, 25), header.getPeriodoInicial());
        assertEquals(LocalDate.of(2018, 9, 25), header.getPeriodoFinal());
        assertEquals("0017799", header.getSequencia());
        assertEquals("FICTI", header.getEmpresaAdquirente());
        assertEquals("01", header.getTipoExtrato());
        assertEquals("A", header.getFiller()); // trim() remove espaços
        assertEquals("002", header.getVersaoLayout());
        assertEquals(".002c", header.getVersaoRelease());
    }
}
