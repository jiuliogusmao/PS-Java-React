package br.com.banco.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.banco.entities.TipoMovimentacao;
import br.com.banco.entities.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransferenciaDTO {
	
	@Id
	private long id;
	
	@NotNull
	private LocalDateTime dataTransferencia;
	
	@NotNull
	private double valor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@NotNull
	private String nomeOperadorTransacao;
	
	public TransferenciaDTO(Transferencia transferencia) {
		super();
		this.id = transferencia.getId();
		this.dataTransferencia = transferencia.getDataTransferencia();
		this.valor = transferencia.getValor();
		this.tipo = transferencia.getTipo();
		this.nomeOperadorTransacao = transferencia.getNomeOperadorTransacao();
	}
	
	
	
}
