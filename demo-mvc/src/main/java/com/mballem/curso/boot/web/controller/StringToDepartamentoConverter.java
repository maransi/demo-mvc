package com.mballem.curso.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.DepartamentoServiceImpl;

@Component
public class StringToDepartamentoConverter implements Converter< String, Departamento > {

	@Autowired
	DepartamentoServiceImpl departamentoService;
	
	public StringToDepartamentoConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Departamento convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		
		try {
			Long id = Long.valueOf(text);
			
			return departamentoService.buscarPorId(id);
		} catch( Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}

}
