package com.equals.concilia.controller;

import com.equals.concilia.model.Header;
import com.equals.concilia.model.Transacao;
import com.equals.concilia.model.Trailer;
import com.equals.concilia.service.ArquivoParserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ParserController {

    private final ArquivoParserService parser;

    public ParserController(ArquivoParserService parser) {
        this.parser = parser;
    }

    @PostMapping("/header")
    public ResponseEntity<Header> parseHeader(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(parser.parseHeader(file));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/transacoes")
    public ResponseEntity<List<Transacao>> parseTransacoes(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(parser.parseTransacoes(file));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/trailer")
    public ResponseEntity<Trailer> parseTrailer(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(parser.parseTrailer(file));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/transacoes")
    public ResponseEntity<List<Transacao>> getTransacoes() {
        return ResponseEntity.ok(parser.loadAllTransacoes());
    }

    @GetMapping("/transacoes/filtradas")
    public ResponseEntity<List<Transacao>> getTransacoesFiltradas(
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam(value = "valorTotalMin", required = false) Double valorTotalMin,
            @RequestParam(value = "valorTotalMax", required = false) Double valorTotalMax,

            @RequestParam(value = "valorLiquidoMin", required = false) Double valorLiquidoMin,
            @RequestParam(value = "valorLiquidoMax", required = false) Double valorLiquidoMax,

            @RequestParam(value = "bandeira", required = false) String bandeira
    ) {
        List<Transacao> todas = parser.loadAllTransacoes();

        List<Transacao> filtradas = todas.stream()
                .filter(tx -> startDate == null || !tx.getDataEvento().isBefore(startDate))
                .filter(tx -> endDate == null || !tx.getDataEvento().isAfter(endDate))
                .filter(tx -> valorTotalMin == null || tx.getValorTotal().compareTo(BigDecimal.valueOf(valorTotalMin)) >= 0)
                .filter(tx -> valorTotalMax == null || tx.getValorTotal().compareTo(BigDecimal.valueOf(valorTotalMax)) <= 0)
                .filter(tx -> valorLiquidoMin == null || tx.getValorLiquido().compareTo(BigDecimal.valueOf(valorLiquidoMin)) >= 0)
                .filter(tx -> valorLiquidoMax == null || tx.getValorLiquido().compareTo(BigDecimal.valueOf(valorLiquidoMax)) <= 0)
                .filter(tx -> bandeira == null || bandeira.isBlank() || bandeira.equalsIgnoreCase(tx.getInstituicaoBandeira()))
                .toList();

        return ResponseEntity.ok(filtradas);
    }


}
