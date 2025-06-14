package com.equals.concilia.config;

import com.equals.concilia.service.ArquivoParserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    private final ArquivoParserService parserService;

    public StartupRunner(ArquivoParserService parserService) {
        this.parserService = parserService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        parserService.carregarTransacoesDeArquivoExemplo();
    }
}
