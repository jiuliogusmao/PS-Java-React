package br.com.banco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.banco.dto.ContaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.repository.ContaRepository;

@Service
public class ContaService {
	
	private ContaRepository contaRepository;

	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	public ResponseEntity<List<ContaDTO>> listarContas(){
		List<Conta> contas = contaRepository.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(
				contas.stream().map(ContaDTO::new)
				.collect(Collectors.toList()));
	}
	

}
