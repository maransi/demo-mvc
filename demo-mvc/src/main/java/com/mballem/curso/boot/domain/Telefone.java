package com.mballem.curso.boot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table( indexes = { @Index( name="idxTelefoneTel",
							columnList="numeroTelefone") } )
public class Telefone extends AbstractEntity<Long> {
	
	@Column( length=2,
				nullable=false)
	@NotNull(message="DDD Inválido")
	@NotBlank(message="DDD Inválido")
	private String ddd;
	
	@Column( length=9,
				nullable=false)
	@NotNull(message="No. Telefônico Inválido")
	@NotBlank(message="No. Telefônico Inválido")
	private String numeroTelefone;

	@ManyToOne
	@JoinColumn(name="empresa_id", 
				foreignKey=@ForeignKey(name="fkTelefone_Emp"))
	private Empresa empresa;
	
	public Telefone(String ddd, String numeroTelefone) {
		super();
		this.ddd = ddd;
		this.numeroTelefone = numeroTelefone;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	@Override
	public String toString() {
		return "\n Telefone [ddd=" + ddd + ", numeroTelefone=" + numeroTelefone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((numeroTelefone == null) ? 0 : numeroTelefone.hashCode());
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
		Telefone other = (Telefone) obj;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (numeroTelefone == null) {
			if (other.numeroTelefone != null)
				return false;
		} else if (!numeroTelefone.equals(other.numeroTelefone))
			return false;
		return true;
	}
	
	
	

}
