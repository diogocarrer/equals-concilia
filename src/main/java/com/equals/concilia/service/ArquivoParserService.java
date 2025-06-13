package com.equals.concilia.service;

import java.util.ArrayList;
import java.util.List;
import com.equals.concilia.model.Transacao;
import org.springframework.stereotype.Service;
import com.equals.concilia.model.Header;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
