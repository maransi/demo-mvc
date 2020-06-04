package com.mballem.curso.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballem.curso.boot.domain.Cargo;
import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.CargoServiceImpl;
import com.mballem.curso.boot.service.DepartamentoServiceImpl;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoServiceImpl cargoService;
	
	
	@Autowired
	private DepartamentoServiceImpl departamentoService;
	
	public CargoController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		
		return "cargo/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		try {
			cargoService.salvar(cargo);
			
			attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		} catch( Exception e) {
			attr.addFlashAttribute("fail", "Ocorreu o seguinte erro ( " + e.getMessage() + " )");
		}
		
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar( @PathVariable("id") Long id, ModelMap model ) {
		try {
			model.addAttribute("cargo", cargoService.buscarPorId(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "cargo/cadastro";
	}

	
	@PostMapping("/editar")
	public String editar( @Valid Cargo cargo, BindingResult result, RedirectAttributes attr ) {

		if (result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		try {
			cargoService.editar(cargo);
			
			attr.addFlashAttribute("success", "Cargo editado com sucesso.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		try {
			if (cargoService.cargoTemFuncionario(id)) {
				model.addAttribute("fail", "Cargo não removido. Possui departamento(s) vinculado(s).");
			} else {
				cargoService.excluir(id);
			
				model.addAttribute("success", "Cargo excluído com sucesso.");
			}

		}catch( Exception e ) {
			e.printStackTrace();
		}
		
		return listar(model);
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos(){
		return departamentoService.buscarTodos();
	}
	
}
