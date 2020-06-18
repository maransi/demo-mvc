package com.mballem.curso.boot.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mballem.curso.boot.dao.EmpresaDao;
import com.mballem.curso.boot.domain.Empresa;
import com.mballem.curso.boot.util.PaginacaoUtil;

@Component
public class EmpresaValidator implements Validator {

	/*
	 * Esse @Autowired só teve sucesso porque a classe EmpresaValidator
	 * foi injetado na classe chamadora EmpresaController e repassado esse 
	 * objeto criado no método  binder.addValidators(empresaValidator);
	 *
	 */
	@Autowired
	private EmpresaDao empresaDao;
	
	
	public EmpresaValidator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Empresa.class.equals(clazz) || PaginacaoUtil.class.equals( clazz );
	}

	@Override
	public void validate(Object target, Errors errors) {
		Empresa empresa = (Empresa) target;
		
		String cnpj = empresa.getCnpj().replaceAll("[^0-9]", "") ;
		
		Empresa empresaCnpj = empresaDao.findByCnpj( cnpj );
		
		if ( empresaCnpj != null && !empresaCnpj.getRazaoSocial().equals(empresa.getRazaoSocial())) {
            errors.rejectValue("cnpj", "CnpjNaoUnico");
		}
	}
}
