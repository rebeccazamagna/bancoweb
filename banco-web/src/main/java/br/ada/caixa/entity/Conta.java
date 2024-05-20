package br.ada.caixa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 10)
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    public Conta() {
        this.dataCriacao = LocalDateTime.now();
    }

    private LocalDateTime dataCriacao;

}
