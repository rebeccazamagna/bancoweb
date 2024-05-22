package br.ada.caixa.controller;


import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import br.ada.caixa.service.OperacoesBancariasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.math.BigDecimal;

@RestController
@RequestMapping("/operacoes")
public class OperacoesBancariasController {

    @Autowired
    private OperacoesBancariasService service;

    @PostMapping("/deposito")
    public void depositar(@RequestBody DepositoRequestDto depositoRequestDto) {
        service.depositar(depositoRequestDto);
    }

    @PostMapping("/saque")
    public void sacar(@RequestBody SaqueRequestDto saqueRequestDto) {
        service.sacar(saqueRequestDto);
    }

    @PostMapping("/transfere")
    public void transferir(@RequestBody TransfereRequestDto transfereRequestDto) {
        service.transferir(transfereRequestDto);
    }

    @GetMapping("/saldo/{numeroConta}")
    public ResponseEntity<SaldoResponseDto> consultarSaldo(@PathVariable String numeroConta) {
        SaldoResponseDto saldoResponseDto = service.consultarSaldo(Long.valueOf(numeroConta));
        return ResponseEntity.status(HttpStatus.OK).body(saldoResponseDto);
    }



}