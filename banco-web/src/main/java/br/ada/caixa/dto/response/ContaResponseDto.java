package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ContaResponseDto {


    @NotNull
    @NotBlank
    private String numero;


    private BigDecimal saldo;

    @NotNull
    @NotBlank
    private String tipo;

}
