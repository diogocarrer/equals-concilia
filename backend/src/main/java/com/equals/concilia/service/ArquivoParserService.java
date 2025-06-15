package com.equals.concilia.service;

import com.equals.concilia.model.Header;
import com.equals.concilia.model.Transacao;
import com.equals.concilia.model.Trailer;
import com.equals.concilia.repository.HeaderRepository;
import com.equals.concilia.repository.TrailerRepository;
import com.equals.concilia.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ArquivoParserService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private HeaderRepository headerRepository;
    @Autowired
    private TrailerRepository trailerRepository;

    public void carregarTransacoesDeArquivoExemplo() throws IOException {
        if (transacaoRepository.count() > 0) {
            System.out.println("Transações já existentes no banco. Ignorando leitura do arquivo.");
            return;
        }

        ClassPathResource res = new ClassPathResource("data/arquivo.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()))) {
            String linha;
            boolean headerSalvo = false;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("0") && !headerSalvo) {
                    Header header = Header.fromLine(linha);
                    headerRepository.save(header);
                    headerSalvo = true;
                } else if (linha.startsWith("1")) {
                    Transacao tx = Transacao.fromLine(linha);
                    transacaoRepository.save(tx);
                } else if (linha.startsWith("9")) {
                    Trailer trailer = Trailer.fromLine(linha);
                    trailerRepository.save(trailer);
                }
            }
        }

        System.out.println("Leitura finalizada. Header, transações e trailer persistidos.");
    }

    public List<Transacao> loadAllTransacoes() {
        return transacaoRepository.findAll();
    }
}
