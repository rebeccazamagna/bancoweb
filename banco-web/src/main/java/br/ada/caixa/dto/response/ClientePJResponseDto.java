package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ClientePJResponseDto {

    @NotNull
    @NotBlank
    private String cnpj;


    private String nomeFantasia;

    @NotNull
    @NotBlank
    private String razaoSocial;

    private List<ContaResponseDto> contas;

}
