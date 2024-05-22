package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TransfereRequestDto {

    @NotNull
    private Long numeroContaOrigem;

    @NotNull
    private Long numeroContaDestino;

    @NotNull
    @NotBlank
    @Min(value = 1)
    private BigDecimal valor;



    @Override
    public String toString() {
        return "TransfereRequestDto{" +
                "numeroContaOrigem='" + numeroContaOrigem + '\'' +
                ", numeroContaDestino='" + numeroContaDestino + '\'' +
                ", valor=" + valor +
                '}';
    }


}
