package br.com.banco.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entities.Transferencia;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
	private TransferenciaService transferenciaService;

	public TransferenciaController(TransferenciaService transferenciaService) {
		this.transferenciaService = transferenciaService;
	}
	
	@GetMapping
	public ResponseEntity<List<TransferenciaDTO>> listarTransferencias(){
		return transferenciaService.listarTransferencias();
	}
	
	@GetMapping("/{conta}")
	public ResponseEntity<List<TransferenciaDTO>> listarTransferenciaPorConta(@PathVariable Long conta){
		return transferenciaService.listarTransferenciasPorConta(conta);
	}
	
	@GetMapping("/findByPeriodo")
	public ResponseEntity<Page<Transferencia>> findByPeriodo(@RequestParam(value="minData", defaultValue = "") String minData, 
	@RequestParam(value="maxData", defaultValue = "") String maxData, 
	Pageable pageable){
		return transferenciaService.findAllByPeriodo(minData, maxData, pageable);
	}

}
