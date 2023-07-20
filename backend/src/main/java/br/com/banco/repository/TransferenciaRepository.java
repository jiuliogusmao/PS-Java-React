package br.com.banco.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{

	List<Transferencia> findAllByConta(Conta conta);
	
	@Query ("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :inicio AND :fim ORDER BY t.dataTransferencia ASC")
	Page<Transferencia> findByPeriodo (@Param ("inicio") OffsetDateTime dataHoraInicio, @Param ("fim") OffsetDateTime dataHoraFim, Pageable pageable);

}
