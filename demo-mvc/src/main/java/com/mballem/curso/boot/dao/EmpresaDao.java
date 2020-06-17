package com.mballem.curso.boot.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.domain.Empresa;
import com.mballem.curso.boot.util.PaginacaoUtil;

@Repository
public class EmpresaDao extends AbstractDao<Empresa, Long> {
	public PaginacaoUtil<Empresa> buscaPaginada( int pagina, String direcao ){
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		
		List<Empresa> empresas = getEntityManager()
								.createQuery("select e from Empresa e order by e.razaoSocial " + direcao, Empresa.class)
								.setFirstResult(inicio)
								.setMaxResults(tamanho)
								.getResultList();
		
		long totalRegistro = count();
		
		long totalPagina = (totalRegistro + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalPagina, direcao, empresas);
	}
	
	public long count() {
		return getEntityManager().createQuery("select count(*) from Empresa", Long.class).getSingleResult();
	}
	
	@Transactional( readOnly = true, propagation = Propagation.SUPPORTS )
	public Empresa findByCnpj( String cnpj ){
		try {
			Empresa empresa = (Empresa) getEntityManager().createNamedQuery("Empresa.findByCnpj")
									 .setParameter("cnpj", cnpj)
									 .getSingleResult();

			return empresa;
		}catch(NoResultException nre) { 
			return null;		
		}catch (Exception e) {
			throw e;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Transactional( readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Empresa> findByName( String razaoSocial ){
		
		try {
			return getEntityManager().createNamedQuery("Empresa.findByName")
									 .setParameter("razaoSocial", "%" + razaoSocial + "%")
									 .getResultList();
		}catch(NoResultException nre) { 
			return null;		
		}catch (Exception e) {
			throw e;
		}
	}
	
}
