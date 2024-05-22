package br.ada.caixa.service;

import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperacoesBancariasService {

    private final ContaRepository contaRepository;

    public void depositar(DepositoRequestDto depositoRequestDto) {
        Conta conta =  contaRepository.findById(depositoRequestDto.getNumeroConta())
                .orElseThrow();
        conta.setSaldo(conta.getSaldo().add(depositoRequestDto.getValor()));
        contaRepository.save(conta);
    }

    public void sacar(SaqueRequestDto saqueRequestDto) {
        Conta conta = contaRepository.findById(saqueRequestDto.getNumeroConta())
                .orElseThrow();
        if(conta.getSaldo().compareTo(saqueRequestDto.getValor()) < 0){
            throw new RuntimeException("Saldo Insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(saqueRequestDto.getValor()));
        contaRepository.save(conta);
    }

    public void transferir(TransfereRequestDto transfereRequestDto) {
        Conta conta1 = contaRepository.findById(transfereRequestDto.getNumeroContaOrigem())
                .orElseThrow();
        Conta conta2 = contaRepository.findById(transfereRequestDto.getNumeroContaDestino())
                .orElseThrow();

        conta1.setSaldo(conta1.getSaldo().subtract(transfereRequestDto.getValor()));
        conta2.setSaldo(conta2.getSaldo().add(transfereRequestDto.getValor()));
        contaRepository.save(conta1);
        contaRepository.save(conta2);
    }

    public SaldoResponseDto consultarSaldo(Long numeroConta) {
        Conta conta =  contaRepository.findById(numeroConta)
                .orElseThrow();
        SaldoResponseDto saldoResponseDto = new SaldoResponseDto();
        saldoResponseDto.setNumeroConta(String.valueOf(numeroConta));
        saldoResponseDto.setSaldo(conta.getSaldo());
        return saldoResponseDto;
    }


}
