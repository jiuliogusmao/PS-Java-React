package br.com.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{

	List<Transferencia> findAllByContas(Conta conta);

}
