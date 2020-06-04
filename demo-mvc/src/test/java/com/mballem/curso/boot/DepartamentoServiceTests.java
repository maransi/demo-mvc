package com.mballem.curso.boot;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

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

import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.DepartamentoServiceImpl;


@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoMvcApplication.class)
@Transactional
class DepartamentoServiceTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DepartamentoServiceImpl service;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		try {
			Departamento departamento = service.buscarPorId(1L);
			
			logger.info("\n DepartamentoServiceImpl.buscarPorId(1) {}", departamento );
			
			assertEquals("Contabilidade", departamento.getNome());
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	@Test
	@Order(2)		// Direciona a ordem da execução dos testes
	void testFindAll() throws Exception {
		logger.info("\n Testing is Running findAll()");
		
		try {
			List<Departamento> departamentos = service.buscarTodos();
			
			assertNotEquals(0, departamentos.size());
			
			logger.info("\n DepartamentoService.findAll() {}", departamentos );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateDepartamento() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Departamento departamento = service.buscarPorId(1L);
			
			departamento.setNome("Contabilidade Updated");
			
			service.editar(departamento);
			
			departamento = service.buscarPorId(1L);
			
			assertEquals("Contabilidade Updated", departamento.getNome());		

			logger.info("\n DepartamentoService.editar() {}", departamento );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertDepartamento() throws Exception {
		logger.info("\n Testing is Running insert()");

		try {
			Departamento departamento = new Departamento("DEPARTAMENTO Inserted", null);
			
			service.salvar(departamento);
			
			assertNotEquals(0, departamento.getId());		

			logger.info("\n DepartamentoService.salvar() {}", departamento );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteDepartamento() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Departamento departamento = new Departamento("DEPARTAMENTO Inserted", null);
			
			service.salvar(departamento);
			
			
			service.excluir(departamento.getId());
			
			
			Departamento departamento2 = service.buscarPorId(departamento.getId());
			
			assertNull( departamento2 );		
			
			logger.info("\n DepartamentoService.excluir() {}", service.buscarTodos() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
