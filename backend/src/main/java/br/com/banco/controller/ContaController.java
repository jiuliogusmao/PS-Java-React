package br.com.banco.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dto.ContaDTO;
import br.com.banco.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	private ContaService contaService;

	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
	@GetMapping
	public ResponseEntity<List<ContaDTO>> listarContas(){
		return contaService.listarContas();
	}
	

}
