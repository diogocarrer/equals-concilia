package com.equals.concilia.service;

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
}
