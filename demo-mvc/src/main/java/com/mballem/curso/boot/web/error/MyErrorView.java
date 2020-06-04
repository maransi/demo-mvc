package com.mballem.curso.boot.web.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyErrorView implements ErrorViewResolver {

	public MyErrorView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		ModelAndView model = new ModelAndView("error");
		
		model.addObject("status", status.value());
		
		if ( status.value() == 404 ) {
			model.addObject("error", "Página não Encontrada.");
			model.addObject("message", "A URL para a Página '" + map.get("path") + "' não Existe.");
		} else if( status.value() == 500 ) {
			model.addObject("error", "Ocorreu um erro Interno no Servidor.");
			model.addObject("message", "Ocorreu um erro inesperado, tente mais tarde.");
		} else {
			model.addObject("error", map.get("error"));
			model.addObject("message", map.get("message"));
			
		}
		
		
		// TODO Auto-generated method stub
		return model;
	}

}
