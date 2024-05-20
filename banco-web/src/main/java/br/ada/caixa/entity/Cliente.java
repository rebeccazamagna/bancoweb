package br.ada.caixa.entity;

import br.ada.caixa.enums.StatusCliente;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_pessoa", discriminatorType = DiscriminatorType.STRING, length = 10)
public class Cliente {

    @Setter
    @Id
    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusCliente status;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Conta> contas;

    @Column(name = "data_de_cadastro")
    private LocalDateTime dataDeCadastro = LocalDateTime.now();

    @JsonCreator
    public static Cliente fromString(String cpf) {
        Cliente cliente = new Cliente();
        cliente.setDocumento(cpf);
        return cliente;
    }

    public Object getTipo() {
        return null;
    }
}