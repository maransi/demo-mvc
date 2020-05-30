package com.mballem.curso.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballem.curso.boot.domain.Cargo;
import com.mballem.curso.boot.service.CargoServiceImpl;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

	@Autowired
	CargoServiceImpl cargoService;
	
	public StringToCargoConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cargo convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		
		try {
			Long id = Long.valueOf(text);
			
			return cargoService.buscarPorId(id);
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}

}
