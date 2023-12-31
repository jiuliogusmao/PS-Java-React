package br.com.banco.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conta")
public class Conta {

	@Id
	@Column(name = "id_conta")
	private long id;

	@Column(name = "nome_responsavel")
	private String nomeResponsavel;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<Transferencia> transferencias;

}
