package br.com.banco.entities;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transferencia {
	
	@Id
	private long id;
	
	@Column (name="data_transferencia", nullable=false, columnDefinition="TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime dataTransferencia = OffsetDateTime.now();
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@Column(name = "nome_operador_transacao")
	private String nomeOperadorTransacao;
	
	@ManyToOne
    @JoinColumn (name = "conta_id")
    private Conta conta;
	
}
