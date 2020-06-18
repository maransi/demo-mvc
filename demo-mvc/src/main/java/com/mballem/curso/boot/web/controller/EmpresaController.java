package com.mballem.curso.boot.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mballem.curso.boot.domain.AreaAtividade;
import com.mballem.curso.boot.domain.Empresa;
import com.mballem.curso.boot.domain.UF;
import com.mballem.curso.boot.service.EmpresaServiceImpl;
import com.mballem.curso.boot.util.PaginacaoUtil;
import com.mballem.curso.boot.web.validator.EmpresaValidator;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	EmpresaServiceImpl empresaService;
	

	@Autowired
	EmpresaValidator empresaValidator;
	
	public EmpresaController() {
		// TODO Auto-generated constructor stub
	}

	@InitBinder
	public void initBinder( WebDataBinder binder ) {
//		binder.addValidators(new EmpresaValidator());
		binder.addValidators(empresaValidator);
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Empresa empresa) {
		return "empresa/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model, 
							@RequestParam("page") Optional<Integer> page,
							@RequestParam("dir") Optional<String> dir) {
		
		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");
		
		PaginacaoUtil<Empresa> pageEmpresa = empresaService.buscarPorPagina(paginaAtual, ordem);
		
		model.addAttribute("pageEmpresa", pageEmpresa );

		return "empresa/lista";
	}
	

	@PostMapping("/salvar")
	public String salvar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "empresa/cadastro";
		}
		
		try {
			empresa.setCnpj(empresa.getCnpj().replaceAll("[^0-9]", ""));
			
			empresaService.salvar(empresa);
			
			attr.addFlashAttribute("success", "Funcionário incluído com sucesso.");
		}catch( Exception e) {
			e.printStackTrace();
			
			attr.addFlashAttribute("fail", "Ocorreu o seguinte erro ( " + e.getMessage() + " )");
		}
		
		return "redirect:/empresas/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar( @PathVariable("id") Long id, ModelMap model ) {
		try {
			model.addAttribute("empresa", empresaService.buscarPorId(id));
		}catch ( Exception e) {
			e.printStackTrace();
		}
		
		return "empresa/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		
		try {
			empresaService.excluir(id);

			attr.addFlashAttribute("success", "Empresa excluido com sucesso.");
		}catch( Exception e ) {
			e.printStackTrace();
		}
		
		return "redirect:/empresas/listar";
	}

	
	@GetMapping("/buscar/razaoSocial")
	public String getNome( @RequestParam("razaoSocial") String razaoSocial, ModelMap model) {
		
		model.addAttribute("empresas", empresaService.buscarPorRazaoSocial(razaoSocial));
		
		return "empresa/lista";
		
	}
	
	@GetMapping("/buscar/cnpj")
	public String getCnpj(@RequestParam("cnpj") String cnpj, ModelMap model) {
		
		if ( ! "".equals( cnpj ) ) {
			model.addAttribute("empresas", empresaService.buscarPorCnpj(cnpj));
		} else {
			model.addAttribute("empresas", empresaService.buscarTodos());
		}
		
		return "empresa/lista";
		
	}
	
	
	@PostMapping("/editar")
	public String editar( @Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {
		if ( result.hasErrors() ) {
			return "/empresa/cadastro";
		}
		try {
			empresa.setCnpj(empresa.getCnpj().replaceAll("[^0-9]", ""));

			empresaService.editar(empresa);
			
			attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
		} catch( Exception e ) {
			e.printStackTrace();
			
			attr.addFlashAttribute("fail", "Ocorreu o seguinte erro ( " + e.getMessage() + " )");
		}
		
		
		return "redirect:/empresas/cadastrar";
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}

	@ModelAttribute("areas")
	public List<AreaAtividade> getAreaAtividade(){
		
		return empresaService.buscarAreaAtividade(); 
		
	}
/*	
	
	@ModelAttribute("departamentos")
	public List<Departamento> getDepartamentos(){
		
		List<Departamento> deptos =  departamentoService.buscarTodos();
		
		return deptos;
		
	}
*/	
}
