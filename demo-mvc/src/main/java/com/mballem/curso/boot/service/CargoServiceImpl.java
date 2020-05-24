package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.CargoDao;
import com.mballem.curso.boot.domain.Cargo;

@Service
public class CargoServiceImpl implements IService<Cargo, Long> {

	@Autowired
	CargoDao dao;
	
	public CargoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public void salvar(Cargo cargo) throws Exception {
		dao.insert(cargo);
	}

	@Override
	@Transactional
	public void editar(Cargo cargo) throws Exception {
		dao.update(cargo);
	}

	@Override
	public void excluir(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public Cargo buscarPorId(Long id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Cargo> buscarTodos() {
		return dao.findAll();
	}

}
