package com.example.gent.service.webclient;

import com.example.gent.entity.Endereco;
import org.springframework.web.reactive.function.client.WebClient;

public class CepService {

    private final WebClient webClient;


    public CepService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://brasilapi.com.br/api/cep/v1").build();
    }

    public Endereco findEnderecoByCep(String cep) {
        String endpoint = "/{cep}";

        return webClient.get()
                .uri(endpoint, cep)
                .retrieve()
                .bodyToMono(Endereco.class)
                .block(); // block() is used here for simplicity; in a real application, consider using reactive programming.
    }

}
