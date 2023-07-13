package br.com.banco.entities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Transferencia {
	
	private Long id;
	private LocalDateTime dataTransferencia;
	private double valor;
	private TipoMovimentacao tipo;
	private Long idConta;
	
}
