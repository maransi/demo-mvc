package com.mballem.curso.boot.domain;

public enum SITUACAO {
	A( "A", "Ativo"),
	I( "I", "Inativo");

	private String sigla;
	private String descricao;
	
	SITUACAO(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	
}
