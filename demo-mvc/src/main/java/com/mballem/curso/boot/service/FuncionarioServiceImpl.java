package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.FuncionarioDao;
import com.mballem.curso.boot.domain.Funcionario;

@Service
public class FuncionarioServiceImpl implements IService<Funcionario, Long> {

	@Autowired
	FuncionarioDao dao;
	
	public FuncionarioServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public void salvar(Funcionario funcionario) throws Exception {
		dao.insert(funcionario);
	}

	@Override
	@Transactional
	public void editar(Funcionario funcionario) throws Exception {
		dao.update(funcionario);
	}

	@Override
	@Transactional
	public void excluir(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public Funcionario buscarPorId(Long id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Funcionario> buscarTodos() {
		return dao.findAll();
	}

}
