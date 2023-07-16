package br.com.banco.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

	@ManyToMany(mappedBy = "contas")
	private List<Transferencia> transferencias;

}
