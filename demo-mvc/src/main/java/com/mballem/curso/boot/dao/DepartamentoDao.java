package com.mballem.curso.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mballem.curso.boot.domain.Cargo;
import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.util.PaginacaoUtil;

@Repository
public class DepartamentoDao extends AbstractDao<Departamento, Long> {

	public DepartamentoDao() {
		// TODO Auto-generated constructor stub
	}
	
	public PaginacaoUtil<Departamento> buscaPaginada( int pagina, String direcao ){
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		
		List<Departamento> departamentos = getEntityManager()
								.createQuery("select d from Departamento d order by d.nome " + direcao, Departamento.class)
								.setFirstResult(inicio)
								.setMaxResults(tamanho)
								.getResultList();
		
		long totalRegistro = count();
		
		long totalPagina = (totalRegistro + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalPagina, direcao, departamentos);
	}
	
	public long count() {
		return getEntityManager().createQuery("select count(*) from Departamento", Long.class).getSingleResult();
	}

}
