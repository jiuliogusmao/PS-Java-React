package br.com.banco.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	private TransferenciaRepository transferenciaRepository;
	private ContaRepository contaRepository;

	public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaRepository contaRepository) {
		this.transferenciaRepository = transferenciaRepository;
		this.contaRepository = contaRepository;
	}

	public ResponseEntity<List<TransferenciaDTO>> listarTransferencias() {
		List<Transferencia> transferencias = transferenciaRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK)
				.body(transferencias.stream().map(TransferenciaDTO::new).collect(Collectors.toList()));
	}

	public ResponseEntity<List<TransferenciaDTO>> listarTransferenciasPorConta(Long conta) {
		if (conta <= 0) {
			return ResponseEntity.badRequest().build();
		}
		Optional<Conta> optionalConta = contaRepository.findById(conta);
		if (!optionalConta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Conta contaBancaria = optionalConta.get();
		List<Transferencia> transferencias = transferenciaRepository.findAllByConta(contaBancaria);
		List<TransferenciaDTO> transferenciasDto = transferencias.stream().map(TransferenciaDTO::new)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(transferenciasDto);
	}

	public ResponseEntity<Page<Transferencia>> buscarPorPeriodo(LocalDate inicio, LocalDate fim, Pageable pageable) {

		ZoneOffset offset = ZoneOffset.of("-03:00");
	    OffsetDateTime dataHoraInicio = inicio.atStartOfDay().atOffset(offset);
	    OffsetDateTime dataHoraFim = fim.atTime(LocalTime.MAX).atOffset(offset);
	    
		if (dataHoraInicio == null) {
			return ResponseEntity.badRequest().body(null); // retornar um erro 400 se o inicio for nulo
		}


		Page<Transferencia> transferencias = transferenciaRepository.findByPeriodo(dataHoraInicio, dataHoraFim,
				pageable);
		return ResponseEntity.ok(transferencias);
	}
	
	public ResponseEntity<List<TransferenciaDTO>> listarPorNomeOperador(String nomeOperador){
		List<Transferencia> transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
		List<TransferenciaDTO> transferenciaDto = transferencias.stream().map(TransferenciaDTO::new).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(transferenciaDto);
	}

}
