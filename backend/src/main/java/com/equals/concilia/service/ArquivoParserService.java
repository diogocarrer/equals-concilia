package com.equals.concilia.service;

import com.equals.concilia.model.Header;
import com.equals.concilia.model.Transacao;
import com.equals.concilia.model.Trailer;

import com.equals.concilia.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TransacaoRepository transacaoRepository;

    public void carregarTransacoesDeArquivoExemplo() throws IOException {
        ClassPathResource res = new ClassPathResource("data/arquivo.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()))) {
            List<Transacao> lista = new ArrayList<>();
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("1")) {
                    Transacao tx = Transacao.fromLine(linha);
                    transacaoRepository.save(tx);
                }
            }
        }
    }

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
                    Transacao tx = Transacao.fromLine(linha);
                    transacaoRepository.save(tx);
                    lista.add(tx);
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

    public List<Transacao> loadAllTransacoes() {
        return transacaoRepository.findAll();
    }
}
