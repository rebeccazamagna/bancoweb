package br.ada.caixa.service;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.exceptions.ContaNotFoundException;
import br.ada.caixa.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class ContaService {
    private final ContaRepository contaRepository;

    private static final double TRANSACTION_FEE = 0.005;

    @Transactional(readOnly = true)
    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Conta> findContaById(Long id) {
        return contaRepository.findById(id);
    }

    @Transactional
    public Conta createConta(Conta conta) {
        return contaRepository.save(conta);
    }

    @Transactional
    public Optional<Conta> updateConta(Long id, Conta contaDetails) {
        return contaRepository.findById(id)
                .map(conta -> {
                    conta.setSaldo(contaDetails.getSaldo());
                    conta.setCliente(contaDetails.getCliente());
                    return contaRepository.save(conta);
                });
    }

    @Transactional
    public void deleteConta(Long id) {
        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
        }
    }

    @Transactional
    public void sacar(Long id, BigDecimal amount) throws Exception {
        Conta conta = findContaById(id)
                .orElseThrow(() -> new Exception("Conta not found"));
        BigDecimal novoSaldo = conta.getSaldo().subtract(amount);
        if (conta.getCliente().getTipo().equalsIgnoreCase("PJ")) {
            BigDecimal transactionFee = TRANSACTION_FEE.multiply(amount);
            novoSaldo = novoSaldo.subtract(transactionFee);
        }
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Insufficient balance");
        }
        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);
    }

    @Transactional
    public void depositar(Long id, double amount) throws Exception {
        Conta conta = findContaById(id)
                .orElseThrow(() -> new Exception("Conta not found"));
        BigDecimal balance = conta.getSaldo();
        BigDecimal amountDecimal = BigDecimal.valueOf(amount);
        BigDecimal newBalance = balance.add(amountDecimal);
        conta.setSaldo(newBalance);
        contaRepository.save(conta);
    }

    @Transactional
    public void transferir(Long fromId, Long toId, double amount) throws Exception {
        Conta fromConta = findContaById(fromId)
                .map(conta -> {
                    BigDecimal amountBigDecimal = BigDecimal.valueOf(amount);
                    BigDecimal newFromBalance = conta.getSaldo().subtract(amountBigDecimal);
                    if (conta.getCliente().getTipo().equals("PJ")) {
                        newFromBalance = newFromBalance.subtract(BigDecimal.valueOf(TRANSACTION_FEE).multiply(amountBigDecimal));
                    }
                    if (newFromBalance.compareTo(BigDecimal.ZERO) < 0) {
                        throw new RuntimeException("Saldo insuficiente");
                    }
                    conta.setSaldo(newFromBalance);
                    return conta;
                })
                .orElseThrow(() -> new ContaNotFoundException("Conta 'From' não encontrada"));

        Conta toConta = findContaById(toId)
                .map(conta -> {
                    BigDecimal newToBalance = conta.getSaldo().add(BigDecimal.valueOf(amount));
                    conta.setSaldo(newToBalance);
                    return conta;
                })
                .orElseThrow(() -> new ContaNotFoundException("Conta 'To' não encontrada"));

        saveConta(fromConta);
        saveConta(toConta);
    }

    @Transactional
    public Conta saveConta(Conta conta) {
        return contaRepository.save(conta);
    }

    @Transactional
    public void investir(Long id, BigDecimal amount) throws Exception {
        Conta conta = findContaById(id)
                .orElseThrow(() -> new Exception("Conta not found"));
        BigDecimal novoSaldo = conta.getSaldo().subtract(amount);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Insufficient balance for investment");
        }
        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);
    }
}