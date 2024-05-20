package br.ada.caixa.controller;

import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.service.ClientePJService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes/pj")
public class ClientePJController {

    private final ClientePJService clientePJService;

    public ClientePJController(ClientePJService clientePJService) {
        this.clientePJService = clientePJService;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegistrarClientePJRequestDto clienteDto) {
        clientePJService.registrarPJ(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<List<ClientePJResponseDto>> listarTodosPJ() {
        return ResponseEntity.ok(clientePJService.listarTodosPJ());
    }

}
