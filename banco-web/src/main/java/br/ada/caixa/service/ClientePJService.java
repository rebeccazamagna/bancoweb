package br.ada.caixa.service;

import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.enums.StatusCliente;
import br.ada.caixa.repository.ClienteRespository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.TransactionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientePJService extends ClienteService{

    private final static Double Transaction_FeePJ = 0.05;


    public ClientePJService(ClienteRespository clienteRespository, ModelMapper modelMapper) {
        super(clienteRespository, modelMapper);
    }



}
