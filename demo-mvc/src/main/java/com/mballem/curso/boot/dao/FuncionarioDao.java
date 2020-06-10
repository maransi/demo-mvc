package com.mballem.curso.boot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Funcionario;
import com.mballem.curso.boot.util.PaginacaoUtil;

@Repository
public class FuncionarioDao extends AbstractDao<Funcionario, Long>{

	public FuncionarioDao() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarPorNome(String nome) {
		return getEntityManager().createNamedQuery("funcionario.nome")
								.setParameter("nome", "%" + nome + "%")
								.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarPorCargo( Long id ){
		
		return getEntityManager().createNamedQuery("funcionario.cargo")
								 .setParameter("cargo", id)
								 .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> buscaPorData( LocalDate dataInicial, LocalDate dataFinal ){
		return getEntityManager().createNamedQuery("funcionario.data")
								 .setParameter("dataInicial", dataInicial)
								 .setParameter("dataFinal", dataFinal )
								 .getResultList();
	}
	
	public PaginacaoUtil<Funcionario> buscaPaginada( int pagina, String direcao ){
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		
		List<Funcionario> funcionarios = getEntityManager()
								.createQuery("select f from Funcionario f order by f.nome " + direcao, Funcionario.class)
								.setFirstResult(inicio)
								.setMaxResults(tamanho)
								.getResultList();
		
		long totalRegistro = count();
		
		long totalPagina = (totalRegistro + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalPagina, direcao, funcionarios);
	}
	
	public long count() {
		return getEntityManager().createQuery("select count(*) from Funcionario", Long.class).getSingleResult();
	}

	
}
