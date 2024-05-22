package br.ada.caixa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("Conta_Poupanca")
public class ContaPoupanca extends Conta {
}
