package br.ada.caixa.service;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.entity.ContaCorrente;
import br.ada.caixa.enums.StatusCliente;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ClienteRespository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class ClienteService {

    private final ClienteRespository clienteRespository;
    private final ModelMapper modelMapper;

    public ClienteService(ClienteRespository clienteRespository, ModelMapper modelMapper) {
        this.clienteRespository = clienteRespository;
        this.modelMapper = modelMapper;
    }

    public void registrarPJ(RegistrarClientePJRequestDto clienteDto) {
        ClientePJ cliente = modelMapper.map(clienteDto, ClientePJ.class);
        cliente.setStatus(StatusCliente.ATIVO);
        clienteRespository.save(cliente);
    }
    public List<ClientePJResponseDto> listarTodosPJ() {
        List<ClientePJ> clientes = clienteRespository.findAllPJ();
        return clientes.stream()
                .map(clientePJ -> modelMapper.map(clientePJ, ClientePJResponseDto.class))
                .collect(Collectors.toList());
    }

    public void registrarPF(RegistrarClientePFRequestDto clienteDto) {
        ClientePF cliente = modelMapper.map(clienteDto, ClientePF.class);
        cliente.setStatus(StatusCliente.ATIVO);

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setCliente(cliente);
        contaCorrente.setSaldo(BigDecimal.ZERO);

        cliente.setContas(new ArrayList<>());
        cliente.getContas().add(contaCorrente);

        clienteRespository.save(cliente);
    }

    public List<ClienteResponseDto> listarTodos() {
        List<Cliente> clientes = clienteRespository.findAll();
        return clientes.stream().map(cliente -> {
            ClienteResponseDto clienteResponseDto = modelMapper.map(cliente, ClienteResponseDto.class);
            clienteResponseDto.setTipo((cliente instanceof ClientePF) ? "PF" : "PJ");
            return clienteResponseDto;
        }).collect(Collectors.toList());
    }

    public List<ClientePFResponseDto> listarTodosPF() {
        List<ClientePF> clientes = clienteRespository.findAllPF();
        return clientes.stream()
                .map(clientePF -> modelMapper.map(clientePF, ClientePFResponseDto.class))
                .collect(Collectors.toList());
    }


    public ClientePFResponseDto buscarClientePorId(String documento) {
        return clienteRespository.findById(documento)
                .map(cliente -> modelMapper.map(cliente, ClientePFResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Cliente nao encontrado!"));
    }
}
