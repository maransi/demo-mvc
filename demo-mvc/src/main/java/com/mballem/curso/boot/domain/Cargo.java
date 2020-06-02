package com.mballem.curso.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name = "cargos",
		indexes = { @Index(name="idxCargoNome", columnList="nome", unique = true)})
public class Cargo extends AbstractEntity<Long> {

	@NotBlank(message="O nome do cargo é obrigatório.")
	@Size(min = 3, max=60, message="O tamanho do nome deve ser entre {min} e {max}.")
	@Column( name = "nome", nullable = false, length=60)
	private String nome;
	
	@NotNull(message="Selecione o departamento relativo ao cargo.")
	@ManyToOne
	@JoinColumn(name="id_departamento_fk",
			foreignKey = @ForeignKey(name = "fkCargo_Depto") )	
	private Departamento departamento;
	
	@OneToMany( mappedBy = "cargo" )
	private List<Funcionario> funcionarios;
	
	public Cargo() {
		// TODO Auto-generated constructor stub
	}

	public Cargo(String nome, Departamento departamento) {
		super();
		this.nome = nome;
		this.departamento = departamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n Cargo [nome=" + nome + ", departamento=" + departamento + "]";
	}

}
