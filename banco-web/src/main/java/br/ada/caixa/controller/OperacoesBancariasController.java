package br.ada.caixa.controller;


import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/operacoes")
public class OperacoesBancariasController {

    @PostMapping("/deposito")
    public void depositar(@RequestBody DepositoRequestDto depositoRequestDto) {
        System.out.println(depositoRequestDto);
    }

    @PostMapping("/saque")
    public void sacar(@RequestBody SaqueRequestDto saqueRequestDto) {
        System.out.println(saqueRequestDto);
    }

    @PostMapping("/transfere")
    public void transferir(@RequestBody TransfereRequestDto transfereRequestDto) {
        System.out.println(transfereRequestDto);
    }

    @GetMapping("/saldo/{numeroConta}")
    public ResponseEntity<SaldoResponseDto> consultarSaldo(@PathVariable String numeroConta) {
        System.out.println("numeroConta = " + numeroConta);

        SaldoResponseDto saldoResponseDto = new SaldoResponseDto();
        saldoResponseDto.setNumeroConta(numeroConta);
        saldoResponseDto.setSaldo(BigDecimal.valueOf(220.00));

        return ResponseEntity.status(HttpStatus.OK).body(saldoResponseDto);
    }

    @PostMapping("/investimento")
    public void investir() {

    }

}
