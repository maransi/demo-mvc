package com.mballem.curso.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "departamentos", 
		indexes= { @Index( name = "idxDeptoNome", columnList = "nome") } )
public class Departamento extends AbstractEntity<Long> {

	@NotBlank(message = "Informe um nome.")
	@Size( min = 3, max = 60, message="O nome do departamento deve ter entre {min} e {max} caracteres.")
	@Column( name = "nome", nullable = false, length=60)
	private String nome;
	
	@OneToMany( mappedBy = "departamento")
	private List<Cargo> cargos;
	
	public Departamento() {
		// TODO Auto-generated constructor stub
	}

	public Departamento(String nome, List<Cargo> cargos) {
		super();
		this.nome = nome;
		this.cargos = cargos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cargos == null) ? 0 : cargos.hashCode());
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
		Departamento other = (Departamento) obj;
		if (cargos == null) {
			if (other.cargos != null)
				return false;
		} else if (!cargos.equals(other.cargos))
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
		return "\n Departamento [nome=" + nome + "]";
	}
	

}
