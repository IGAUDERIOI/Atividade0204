package br.com.senac.atv0204.Repositorio;

import br.com.senac.atv0204.Entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clienteRepositorio extends JpaRepository<Clientes, Long> {
}
