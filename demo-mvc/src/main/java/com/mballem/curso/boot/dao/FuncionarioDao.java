package com.mballem.curso.boot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Funcionario;

@Repository
public class FuncionarioDao extends AbstractDao<Funcionario, Long>{

	public FuncionarioDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Funcionario> buscarPorNome(String nome) {
		return getEntityManager().createNamedQuery("funcionario.nome")
								.setParameter("nome", "%" + nome + "%")
								.getResultList();
	}

	public List<Funcionario> buscarPorCargo( Long id ){
		
		return getEntityManager().createNamedQuery("funcionario.cargo")
								 .setParameter("cargo", id)
								 .getResultList();
	}
	
	public List<Funcionario> buscaPorData( LocalDate dataInicial, LocalDate dataFinal ){
		return getEntityManager().createNamedQuery("funcionario.data")
								 .setParameter("dataInicial", dataInicial)
								 .setParameter("dataFinal", dataFinal )
								 .getResultList();
	}
	
}
