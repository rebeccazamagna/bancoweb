package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegistrarClientePFRequestDto {

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String dataNascimento;

}
