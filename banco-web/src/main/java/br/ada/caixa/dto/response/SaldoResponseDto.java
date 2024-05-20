package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class SaldoResponseDto {

    @NotBlank
    @NotNull
    private String numeroConta;
    private BigDecimal saldo;


}
