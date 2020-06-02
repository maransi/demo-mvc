package com.mballem.curso.boot.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballem.curso.boot.domain.Cargo;
import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.domain.Funcionario;
import com.mballem.curso.boot.domain.UF;
import com.mballem.curso.boot.service.CargoServiceImpl;
import com.mballem.curso.boot.service.DepartamentoServiceImpl;
import com.mballem.curso.boot.service.FuncionarioServiceImpl;
import com.mballem.curso.boot.web.validator.FuncionarioValidator;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioServiceImpl funcionarioService;
	
	@Autowired
	CargoServiceImpl cargoService;

	@Autowired
	DepartamentoServiceImpl departamentoService;
	
	public FuncionarioController() {
		// TODO Auto-generated constructor stub
	}

	@InitBinder
	public void initBinder( WebDataBinder binder ) {
		binder.addValidators(new FuncionarioValidator());
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
        model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		
		return "/funcionario/lista";
	}
	

	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "/funcionario/cadastro";
		}
		
		try {
			funcionarioService.salvar(funcionario);
			
			attr.addFlashAttribute("success", "Funcionário incluído com sucesso.");
		}catch( Exception e) {
			e.printStackTrace();
			
			attr.addFlashAttribute("fail", "Ocorreu o seguinte erro ( " + e.getMessage() + " )");
		}
		
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar( @PathVariable("id") Long id, ModelMap model ) {
		try {
			model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		}catch ( Exception e) {
			e.printStackTrace();
		}
		
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir( @PathVariable("id") Long id, ModelMap model) {
		try {
			funcionarioService.excluir(id);
			
			model.addAttribute("success", "Funcionário eliminado com sucesso.");
		}catch ( Exception e ) {
			model.addAttribute("fail", "Ocorreu o seguinte erro ( " + e.getMessage() + " )");
//			e.printStackTrace();
			
		}
		
		return listar( model );
	}

	
	@GetMapping("/buscar/nome")
	public String getNome( @RequestParam("nome") String nome, ModelMap model) {
		
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		
		return "/funcionario/lista";
		
	}
	
	@GetMapping("/buscar/cargo")
	public String getCargo(@RequestParam("id") Long id, ModelMap model) {
		
		if ( id > 0 ) {
			model.addAttribute("funcionarios", funcionarioService.buscarPorCargo(id));
		} else {
			model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		}
		
		return "/funcionario/lista";
		
	}
	
	@GetMapping("/buscar/data")
	public String getData(@RequestParam("entrada") 
							@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial, 
							@RequestParam("saida") 
							@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal, 
							ModelMap model ) {
		
		model.addAttribute("funcionarios", funcionarioService.buscarPorData(dataInicial, dataFinal));
		
		return "/funcionario/lista";
	}
	
	@PostMapping("/editar")
	public String editar( @Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		if ( result.hasErrors() ) {
			return "/funcionario/cadastro";
		}
		try {
			funcionarioService.editar(funcionario);
			
			attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
		} catch( Exception e ) {
			e.printStackTrace();
			
			attr.addFlashAttribute("fail", "Ocorreu o seguinte erro ( " + e.getMessage() + " )");
		}
		
		
		return "redirect:/funcionarios/cadastrar";
	}
	
	
	@ModelAttribute("cargos")
	public List<Cargo> getDeCargos(){
		
		return cargoService.buscarTodos();
		
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> getDepartamentos(){
		
		List<Departamento> deptos =  departamentoService.buscarTodos();
		
		return deptos;
		
	}
	
}
