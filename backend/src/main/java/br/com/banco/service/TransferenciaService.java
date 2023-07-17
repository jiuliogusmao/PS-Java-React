package br.com.banco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
	
	private TransferenciaRepository transferenciaRepository;

	public TransferenciaService(TransferenciaRepository transferenciaRepository) {
		this.transferenciaRepository = transferenciaRepository;
	}
	
	public ResponseEntity<List<TransferenciaDTO>> listarTransferencias(){
		List<Transferencia> transferencias = transferenciaRepository.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(
				transferencias.stream().map(TransferenciaDTO::new)
				.collect(Collectors.toList()));
	}

}
