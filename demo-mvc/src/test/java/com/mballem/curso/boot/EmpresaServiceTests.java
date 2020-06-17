package com.mballem.curso.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.AreaAtividadeRepository;
import com.mballem.curso.boot.domain.AreaAtividade;
import com.mballem.curso.boot.domain.Empresa;
import com.mballem.curso.boot.domain.Endereco;
import com.mballem.curso.boot.domain.SITUACAO;
import com.mballem.curso.boot.domain.UF;
import com.mballem.curso.boot.service.EmpresaServiceImpl;

@TestMethodOrder(OrderAnnotation.class) // Annotation para informar que a ordem da execução dos testes será pela
										// annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoMvcApplication.class)
@Transactional
class EmpresaServiceTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmpresaServiceImpl service;

	@Autowired
	AreaAtividadeRepository areaAtividadeRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	@Rollback(true)
	@Order(1) // Direciona a ordem da execução dos testes
	void testCrud() throws Exception {
		
		// Busca por CNPJ
		Empresa empresaCnpj = service.buscarPorCnpj("103");

		assertNull( empresaCnpj );

		// Buscar por Id
		logger.info("\n Testing is Running findById()");

		Empresa empresaPorId = service.buscarPorId(1L);

		assertNotNull( empresaPorId );

		logger.info("\n EmpresaServiceImpl.buscarPorCnpj(1) {}", empresaPorId);

		// Buscar Todos
		List<Empresa> empresas = service.buscarTodos();
		
		assertNotNull( empresas );
		
		logger.info("\n EmpresaServiceImpl.buscarTodos() {}", empresas.get(0));
		
		// Buscar por Razao Social
		List<Empresa> empresasPorRazaoSocial = service.buscarPorRazaoSocial("PortoCred");

		assertNotNull( empresasPorRazaoSocial );

		logger.info("\n EmpresaServiceImpl.buscarPorRazaoSocial(0) {}", empresasPorRazaoSocial.get(0));
		
		// Insert
		service.salvar(this.insertEmpresaTeste("04230630000103"));

		Empresa empresaPorCnpj = service.buscarPorCnpj("04230630000103");

		assertEquals("JBCred Financeira", empresaPorCnpj.getRazaoSocial());

		logger.info("\n EmpresaServiceImpl.salvar() {}", empresaPorCnpj);

		// Update
		empresaPorCnpj.setRazaoSocial("JBCred Financeira UPDATED");
		
		service.editar(empresaPorCnpj);
		
		Empresa empresaUpdated = service.buscarPorCnpj("04230630000103");
		
		assertEquals("JBCred Financeira UPDATED", empresaUpdated.getRazaoSocial());
		
		logger.info("\n EmpresaServiceImpl.editar() {}", empresaUpdated);
		
		// Delete
		service.excluir(empresaUpdated.getId());
		
		Empresa empresaDeleted = service.buscarPorId( empresaUpdated.getId() );
		
		assertNull( empresaDeleted );		


		
		
	}
	
	
	
	
	
	
	private Empresa insertEmpresaTeste( String cpfCnpj ) {
		Empresa empresa;
		
		try {
			AreaAtividade areaAtividade = areaAtividadeRepository.findById(1L).get();

			Endereco endereco = new Endereco("CENTRO","01030-003","SAO PAULO", "VIADUTO DO CHÁ","R LIBERO BADARÓ", 83,UF.SP);

			empresa = new Empresa(cpfCnpj, "JBCred Financeira", endereco, SITUACAO.A, LocalDate.of(2001,1,1), areaAtividade);
			
			return empresa;
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
	}
}
