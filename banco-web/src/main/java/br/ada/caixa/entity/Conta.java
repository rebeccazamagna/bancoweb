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
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private BigDecimal saldo;

    @ManyToOne
    private Cliente cliente;
    public Conta() {
        this.dataCriacao = LocalDateTime.now();
    }

    private LocalDateTime dataCriacao;

}
