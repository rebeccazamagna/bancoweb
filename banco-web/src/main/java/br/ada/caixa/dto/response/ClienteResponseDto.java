package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteResponseDto {

    @NotNull
    @NotBlank
    private String documento;

    @NotNull
    @NotBlank
    private String tipo;

}
