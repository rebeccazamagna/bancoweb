package br.ada.caixa.service;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ContaCorrente;
import br.ada.caixa.enums.StatusCliente;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ClienteRespository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientePFService extends ClienteService {


    public ClientePFService(ClienteRespository clienteRespository, ModelMapper modelMapper) {
        super(clienteRespository, modelMapper);
    }
}

