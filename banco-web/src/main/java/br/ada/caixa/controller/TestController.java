package br.ada.caixa.controller;


import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public String Test (){
        return "conexao ok";

    }

}



