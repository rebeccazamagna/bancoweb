package br.ada.caixa.repository;

import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRespository extends JpaRepository<Cliente, String> {

    @Query("select c from ClientePF c")
    List<ClientePF> findAllPF();

    @Query("select c from ClientePJ c")
    List<ClientePJ> findAllPJ();

}
