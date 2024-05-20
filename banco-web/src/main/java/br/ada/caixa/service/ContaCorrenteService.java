package br.ada.caixa.service;

import br.ada.caixa.repository.ContaRepository;

public class ContaCorrenteService extends ContaService {
    public ContaCorrenteService(ContaRepository contaRepository) {
        super(contaRepository);
    }
}
