package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegistrarClientePJRequestDto {

    @NotNull
    @NotBlank
    private String cnpj;

    @NotNull
    @NotBlank
    private String nomeFantasia;

    @NotNull
    @NotBlank
    private String razaoSocial;

}
