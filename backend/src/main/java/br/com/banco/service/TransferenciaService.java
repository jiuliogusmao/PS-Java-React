package br.com.banco.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

	public ResponseEntity<List<TransferenciaDTO>> listarTransferencias(){
		List<Transferencia> transferencias = transferenciaRepository.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(
				transferencias.stream().map(TransferenciaDTO::new)
				.collect(Collectors.toList()));
	}
	
	public ResponseEntity<List<TransferenciaDTO>> listarTransferenciasPorConta(Long conta){
		if (conta <= 0) {
			return ResponseEntity.badRequest().build();
		}
		Optional<Conta>optionalConta = contaRepository.findById(conta);
		if(!optionalConta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Conta contaBancaria = optionalConta.get();
		List<Transferencia> transferencias = transferenciaRepository.findAllByContas(contaBancaria);
		List<TransferenciaDTO> transferenciasDTO = transferencias.stream()
				.map(TransferenciaDTO::new)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(transferenciasDTO);
	}
	
	public ResponseEntity<Page<Transferencia>> findAllByPeriodo(String minData, String maxData, Pageable pageable){
		LocalDateTime atual = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDateTime min = minData.equals("") ? atual.minusDays(365) : LocalDateTime.parse(minData);
		LocalDateTime max = minData.equals("") ? atual : LocalDateTime.parse(maxData);
		
		return ResponseEntity.status(HttpStatus.OK).body(transferenciaRepository.findByPeriodo(min, max, pageable));
	}
	
}
