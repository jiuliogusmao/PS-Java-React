package br.com.banco.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Column(name = "data_transferencia")
	private LocalDateTime dataTransferencia;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@Column(name = "nome_operador_transacao")
	private String nomeOperadorTransacao;
	
	@ManyToMany
    @JoinTable (name = "transferencia_conta",
        joinColumns = @JoinColumn (name = "transferencia_id"),
        inverseJoinColumns = @JoinColumn (name = "conta_id"))
    private List<Conta> contas;
	
}
