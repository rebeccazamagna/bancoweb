package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ClientePFResponseDto {

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String dataNascimento;

    private List<ContaResponseDto> contas;

}
