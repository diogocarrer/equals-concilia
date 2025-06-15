package com.equals.concilia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
            <html>
              <head><title>Equals Concilia</title></head>
              <body style="font-family: sans-serif; text-align: center; margin-top: 50px;">
                <h1>✅ API Equals Concilia está rodando!</h1>
                <p>Use <a href='/api/transacoes'>/api/transacoes</a> para listar transações.</p>
                <p>Acesse o <a href='/h2-console'>H2 Console</a> para visualizar o banco de dados.</p>
              </body>
            </html>
        """;
    }
}
