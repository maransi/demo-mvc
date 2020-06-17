package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.AreaAtividadeRepository;
import com.mballem.curso.boot.dao.EmpresaDao;
import com.mballem.curso.boot.domain.AreaAtividade;
import com.mballem.curso.boot.domain.Empresa;
import com.mballem.curso.boot.util.PaginacaoUtil;

@Service
public class EmpresaServiceImpl implements IService<Empresa, Long> {
	@Autowired
	EmpresaDao dao;
	
	@Autowired
	AreaAtividadeRepository areaAtividadeRepository;
	
	public EmpresaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public void salvar(Empresa empresa) throws Exception {
		dao.insert(empresa);
	}

	@Override
	@Transactional
	public void editar(Empresa empresa) throws Exception {
		dao.update(empresa);
	}

	@Override
	public void excluir(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public Empresa buscarPorId(Long id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Empresa> buscarTodos() {
		return dao.findAll();
	}


	public PaginacaoUtil<Empresa> buscarPorPagina( int pagina, String direcao){
		
		return dao.buscaPaginada(pagina, direcao);
	}

	public Empresa buscarPorCnpj( String cnpj ){
		return dao.findByCnpj(cnpj);
	}
	
	public List<Empresa> buscarPorRazaoSocial( String razaoSocial ){
		return dao.findByName(razaoSocial);
	}
	
	public List<AreaAtividade> buscarAreaAtividade(){
		return areaAtividadeRepository.findAll();
	}

}
