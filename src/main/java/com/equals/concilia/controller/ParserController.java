package com.equals.concilia.controller;

import com.equals.concilia.model.Header;
import com.equals.concilia.model.Transacao;
import com.equals.concilia.model.Trailer;

import com.equals.concilia.service.ArquivoParserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.IOException;

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
            Header header = parser.parseHeader(file);
            return ResponseEntity.ok(header);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/transacoes")
    public ResponseEntity<List<Transacao>> parseTransacoes(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(parser.parseTransacoes(file));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/trailer")
    public ResponseEntity<Trailer> parseTrailer(@RequestParam("file") MultipartFile file) {
        try {
            Trailer trailer = parser.parseTrailer(file);
            return ResponseEntity.ok(trailer);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
