package br.com.banco.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Conta {
	
	@Id
	private long id;
	private String nomeResponsavel;
	
	@ManyToMany (mappedBy = "contas")
    private List<Transferencia> transferencias;

}
