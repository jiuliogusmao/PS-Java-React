package br.com.banco.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	private LocalDateTime dataTransferencia;
	private double valor;
	private TipoMovimentacao tipo;
	private String nomeOperadorTransacao;
	
	@ManyToMany
    @JoinTable (name = "transferencia_conta",
        joinColumns = @JoinColumn (name = "transferencia_id"),
        inverseJoinColumns = @JoinColumn (name = "conta_id"))
    private List<Conta> contas;
	
}
