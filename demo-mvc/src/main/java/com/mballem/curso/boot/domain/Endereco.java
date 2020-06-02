package com.mballem.curso.boot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="enderecos")
public class Endereco extends AbstractEntity<Long> {


	@NotBlank
	@NotNull
	@Column(name="bairro", nullable = false, length = 255)
	private String bairro;
	
	@NotBlank
	@NotNull
	@Size(min=9, max=9, message="{Size.endereco.cep}")
	@Column(name="cep", nullable = false, length = 9)
	private String cep;
	
	@NotBlank
	@NotNull
	@Column(name="cidade", nullable = false, length = 255)
	private String cidade;
	
	
	@Size(max=255)
	@Column(name="complemento", nullable = false, length = 255)
	private String complemento;
	
	@NotBlank
	@Column(name="logradouro", nullable = false, length = 255)
	private String logradouro;
	
	@NotNull(message="{NotNull.endereco.numero}")
	@Digits(integer=5, fraction=0)
	@Column(name="numero", nullable = false, length = 255)
	private Integer numero;

	@NotNull(message="{NotNull.endereco.uf}")
	@Column(name="uf", nullable = false, length = 2)
	@Enumerated(EnumType.STRING)	// Informa ao bd que o conteúdo deverá ser salvo como STRING
	private UF uf;
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(String bairro, String cep, String cidade, String complemento, String logradouro, int numero, UF uf) {
		super();
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.complemento = complemento;
		this.logradouro = logradouro;
		this.numero = numero;
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "\n Endereco [bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", complemento=" + complemento
				+ ", logradouro=" + logradouro + ", numero=" + numero + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + numero;
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}

	
	
}
