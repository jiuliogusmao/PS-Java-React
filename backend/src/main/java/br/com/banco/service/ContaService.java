package br.com.banco.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.banco.entities.Conta;
import br.com.banco.repository.ContaRepository;

@Service
@Transactional
public class ContaService {
	
	private ContaRepository contaRepository;

	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	public ResponseEntity<List<Conta>> listarContas(){
		List<Conta> contas = contaRepository.findAll();
		
		return ResponseEntity.ok(contas);
	}
	

}
