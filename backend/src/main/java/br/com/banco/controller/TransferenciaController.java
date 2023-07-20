package br.com.banco.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<TransferenciaDTO>> listarTransferencias() {
		return transferenciaService.listarTransferencias();
	}

	@GetMapping("/{conta}")
	public ResponseEntity<List<TransferenciaDTO>> listarTransferenciaPorConta(@PathVariable Long conta) {
		return transferenciaService.listarTransferenciasPorConta(conta);
	}

	@GetMapping("/periodo/{inicio}/{fim}")
	public ResponseEntity<Page<Transferencia>> buscarPorPeriodo(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate inicio,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fim,
			@PageableDefault(page = 0, size = 10, sort = "dataTransferencia", direction = Sort.Direction.ASC) Pageable pageable) {
		return transferenciaService.buscarPorPeriodo(inicio, fim, pageable);
	}

}
