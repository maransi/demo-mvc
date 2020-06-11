package com.mballem.curso.boot.domain;

public enum AtivoInativo {
	ATIVO( "A","Ativo"),
	INATIVO( "I", "Inativo");

	private String sigla;
	
	private String descricao;
	
	AtivoInativo(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;	
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
