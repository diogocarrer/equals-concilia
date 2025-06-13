package com.equals.concilia.service;

import com.equals.concilia.model.Header;
import com.equals.concilia.model.Transacao;
import com.equals.concilia.model.Trailer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ClassPathResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArquivoParserService {
    public Header parseHeader(MultipartFile file) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String linha = reader.readLine();
            if (linha != null && linha.startsWith("0")) {
                return Header.fromLine(linha);
            } else {
                throw new IOException("Linha de header inválida ou ausente");
            }
        }
    }

    public List<Transacao> parseTransacoes(MultipartFile file) throws IOException {
        List<Transacao> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.readLine(); // pula header
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("1")) {
                    lista.add(Transacao.fromLine(linha));
                }
            }
        }
        return lista;
    }

    public Trailer parseTrailer(MultipartFile file) throws IOException {
        String linhaTrailer = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("9")) {
                    linhaTrailer = linha;
                    break;
                }
            }
        }
        if (linhaTrailer == null) {
            throw new IOException("Linha de trailer não encontrada");
        }
        return Trailer.fromLine(linhaTrailer);
    }

    public List<Transacao> loadAllTransacoes() throws IOException {
        ClassPathResource res = new ClassPathResource("data/arquivo.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()))) {
            List<Transacao> lista = new ArrayList<>();
            String linha = reader.readLine();
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("1")) {
                    lista.add(Transacao.fromLine(linha));
                } else if (linha.startsWith("9")) {
                    break;
                }
            }
            return lista;
        }
    }
}
