package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mballem.curso.boot.dao.DepartamentoDao;
import com.mballem.curso.boot.domain.Departamento;

@Service
public class DepartamentoServiceImpl implements IService<Departamento, Long> {

	@Autowired
	DepartamentoDao dao;
	
	public DepartamentoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(Departamento depto) throws Exception {
		dao.insert(depto);
	}

	@Override
	public void editar(Departamento depto) throws Exception {
		dao.update( depto );
	}

	@Override
	public void excluir(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public Departamento buscarPorId(Long id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Departamento> buscarTodos() {
		return dao.findAll();
	}

	public boolean departamentoTemCargo(Long id) throws Exception {

		if (buscarPorId(id).getCargos().isEmpty()){
			return false;
		}
		
		return true;
	}

}
