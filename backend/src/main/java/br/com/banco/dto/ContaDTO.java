package br.com.banco.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.banco.entities.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

	@Id
	private long id;
	
	@NotNull
	private String nomeResponsavel;

	public ContaDTO(Conta conta) {
		this.id = conta.getId();
		this.nomeResponsavel = conta.getNomeResponsavel();
	}
	
	
}
