package com.mballem.curso.boot;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.mballem.curso.boot.domain.Cargo;
import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.CargoServiceImpl;
import com.mballem.curso.boot.service.DepartamentoServiceImpl;


@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoMvcApplication.class)
@Transactional
class CargoServiceTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CargoServiceImpl service;
	
	@Autowired
	DepartamentoServiceImpl departamentoService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		try {
			Cargo cargo = service.buscarPorId(1L);
			
			logger.info("\n CargoServiceImpl.buscarPorId(1) {}", cargo );
			
			assertEquals("Auxiliar Contábil", cargo.getNome());
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
			List<Cargo> cargos = service.buscarTodos();
			
			assertNotEquals(0, cargos.size());
			
			logger.info("\n CargoService.findAll() {}", cargos );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateCargo() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Cargo cargo = service.buscarPorId(1L);
			
			cargo.setNome("Auxiliar Contábil Updated");
			
			service.editar(cargo);
			
			cargo = service.buscarPorId(1L);
			
			assertEquals("Auxiliar Contábil Updated", cargo.getNome());		

			logger.info("\n CargoService.editar() {}", cargo );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertCargo() throws Exception {
		logger.info("\n Testing is Running insert()");

/*
 * Acrescentar a inclusão do departamento		
 */
		
		
		try {
			Departamento depto = departamentoService.buscarPorId(1L);
			
			Cargo cargo = new Cargo("CARGO Inserted", depto);
			
			service.salvar(cargo);
			
			assertNotEquals(0, cargo.getId());		

			logger.info("\n CargoService.salvar() {}", cargo );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteCargo() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Departamento depto = departamentoService.buscarPorId(1L);
			
			Cargo cargo = new Cargo("CARGO Inserted", depto);
			
			service.salvar(cargo);
			
			
			service.excluir(cargo.getId());
			
			
			Cargo cargo2 = service.buscarPorId(cargo.getId());
			
			assertNull( cargo2 );		
			
			logger.info("\n CargoService.excluir() {}", service.buscarTodos() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
