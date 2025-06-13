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
    public ResponseEntity<List<Transacao>> getTransacoes(
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
    ) {
        try {
            List<Transacao> todas = parser.loadAllTransacoes();

            if (startDate != null && endDate != null) {
                todas = todas.stream()
                        .filter(tx ->
                                !tx.getDataEvento().isBefore(startDate) &&
                                        !tx.getDataEvento().isAfter(endDate)
                        )
                        .toList();
            }

            return ResponseEntity.ok(todas);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
