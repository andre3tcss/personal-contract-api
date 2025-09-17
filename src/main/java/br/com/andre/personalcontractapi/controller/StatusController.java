package br.com.andre.personalcontractapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta é a sua primeira classe Controller.
 * A anotação @RestController informa ao Spring que esta classe é um
 * "controlador REST", o que significa que ela irá processar requisições web
 * e retornar dados (como JSON ou texto) diretamente no corpo da resposta.
 *
 * Esta anotação é a aplicação direta da teoria que você leu no Dia 5.
 */
@RestController
/**
 * A anotação @RequestMapping("/api") define um prefixo de URL base
 * para todos os métodos ("endpoints") dentro desta classe.
 * Ou seja, todas as rotas que definirmos aqui começarão com /api.
 */
@RequestMapping("/api")
public class StatusController {

    /**
     * A anotação @GetMapping("/status") mapeia este método
     * para requisições HTTP do tipo GET na URL /api/status.
     *
     * Quando um usuário acessar http://localhost:8080/api/status,
     * este método será executado.
     *
     * @return Uma string simples indicando que a API está online.
     */
    @GetMapping("/status")
    public String getStatus() {
        return "API is running... v1.0.0";
    }
}