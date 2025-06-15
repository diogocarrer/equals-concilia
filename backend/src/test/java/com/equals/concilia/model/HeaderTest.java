package com.equals.concilia.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class HeaderTest {

    @Test
    void deveParsearDatasDoHeader() {
        String linha = "012345678912018112920180925201809250017799FICTI01A                    002.002c";
        Header header = Header.fromLine(linha);

        assertEquals(LocalDate.of(2018, 11, 29), header.getDataGeracao());
        assertEquals(LocalDate.of(2018, 9, 25), header.getPeriodoInicial());
        assertEquals("FICTI", header.getEmpresaAdquirente());
    }
}
