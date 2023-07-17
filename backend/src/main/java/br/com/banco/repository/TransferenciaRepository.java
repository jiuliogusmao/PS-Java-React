package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{

	List<Transferencia> findAllByContas(Conta conta);
	
	@Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia.toLocalDate() BETWEEN :min.toLocalDate() AND :max.toLocalDate() ORDER BY t.dataTransferencia ASC")
	Page<Transferencia> findByPeriodo(LocalDateTime min, LocalDateTime max, Pageable pageable);
	
}
