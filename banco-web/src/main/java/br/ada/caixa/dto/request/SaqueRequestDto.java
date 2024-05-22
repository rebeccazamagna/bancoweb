package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class SaqueRequestDto {

    @NotBlank
    @NotNull
    private Long numeroConta;

    @NotBlank
    @NotNull
    @Min(value = 1)
    private BigDecimal valor;


    @Override
    public String toString() {
        return "DepositoRequestDto{" +
                "numeroConta='" + numeroConta + '\'' +
                ", valor=" + valor +
                '}';
    }
}
