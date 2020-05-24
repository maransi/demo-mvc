package com.mballem.curso.boot;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import com.mballem.curso.boot.domain.Endereco;
import com.mballem.curso.boot.domain.Funcionario;
import com.mballem.curso.boot.domain.UF;
import com.mballem.curso.boot.service.CargoServiceImpl;
import com.mballem.curso.boot.service.FuncionarioServiceImpl;


@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoMvcApplication.class)
@Transactional
class FuncionarioServiceTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FuncionarioServiceImpl service;
	
	@Autowired
	CargoServiceImpl serviceCargo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		try {
			Funcionario funcionario = service.buscarPorId(1L);
			
			logger.info("\n FuncionarioServiceImpl.buscarPorId(1) {}", funcionario );
			
			assertEquals("Jorge da Silva", funcionario.getNome());
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
			List<Funcionario> funcionarios = service.buscarTodos();
			
			assertNotEquals(0, funcionarios.size());
			
			logger.info("\n FuncionarioService.findAll() {}", funcionarios );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateFuncionario() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Funcionario funcionario = service.buscarPorId(1L);
			
			funcionario.setNome("Jorge da Silva Updated");
			
			service.editar(funcionario);
			
			funcionario = service.buscarPorId(1L);
			
			assertEquals("Jorge da Silva Updated", funcionario.getNome());		

			logger.info("\n FuncionarioService.editar() {}", funcionario );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertFuncionario() throws Exception {
		logger.info("\n Testing is Running insert()");

/*
 * Acrescentar a inclusão do departamento		
 */
		
		
		try {
			Endereco endereco = new Endereco("JD CARLU","02423100","SAO PAULO","CASA C","R CARIOBA", 360, UF.SP);
			
			Cargo cargo = serviceCargo.buscarPorId(2L);
			
			Funcionario funcionario = new Funcionario();
//new Funcionario("MARCO ANTONIO DA SILVA", new BigDecimal("11000,00"), new LocalDate(0, 0, 0), null, cargo, endereco);
			
			funcionario.setCargo(cargo);
			funcionario.setDataEntrada(LocalDate.now());
			funcionario.setDataSaida(null);
			funcionario.setEndereco(endereco);
			funcionario.setNome("MARCO ANTONIO DA SILVA");
			funcionario.setSalario(new BigDecimal("11000.00"));
			
			service.salvar(funcionario);
			
			assertNotEquals(0, funcionario.getId());		

			logger.info("\n FuncionarioService.salvar() {}", funcionario );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteFuncionario() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Endereco endereco = new Endereco("JD CARLU","02423100","SAO PAULO","CASA C","R CARIOBA", 360, UF.SP);
			
			Cargo cargo = serviceCargo.buscarPorId(2L);
			
			Funcionario funcionario = new Funcionario();
//new Funcionario("MARCO ANTONIO DA SILVA", new BigDecimal("11000,00"), new LocalDate(0, 0, 0), null, cargo, endereco);
			
			funcionario.setCargo(cargo);
			funcionario.setDataEntrada(LocalDate.now());
			funcionario.setDataSaida(null);
			funcionario.setEndereco(endereco);
			funcionario.setNome("MARCO ANTONIO DA SILVA");
			funcionario.setSalario(new BigDecimal("11000.00"));
			
			service.salvar(funcionario);
			
			service.excluir(funcionario.getId());
			
			Funcionario funcionario2 = service.buscarPorId(funcionario.getId());
			
			assertNull( funcionario2 );		
			
			logger.info("\n FuncionarioService.excluir() {}", service.buscarTodos() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
