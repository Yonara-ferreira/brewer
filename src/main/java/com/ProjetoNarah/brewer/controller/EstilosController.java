package com.ProjetoNarah.brewer.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProjetoNarah.brewer.controller.page.PageWrapper;
import com.ProjetoNarah.brewer.model.Estilo;
import com.ProjetoNarah.brewer.repository.Estilos;
import com.ProjetoNarah.brewer.repository.filter.EstiloFilter;
import com.ProjetoNarah.brewer.service.CadastroEstiloService;
import com.ProjetoNarah.brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping("/estilos")
public class EstilosController {
	
	@Autowired
	private Estilos estilos;

	
	@Autowired
	private CadastroEstiloService cadastroEstiloService;
	

	@RequestMapping(value = "/novo", method = RequestMethod.GET)	
	public ModelAndView novo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("estilos/CadastroEstilos");
		mv.addObject("estilos", estilos.findAll());
		return mv;
		
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {			
			return novo(estilo);
		}
		try {
			cadastroEstiloService.salvar(estilo);
			attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");
		} catch (NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso ");
		return new ModelAndView ("redirect:/estilos/novo");
	}			
	
	@RequestMapping( method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity <?> salvar(@RequestBody  @Valid  Estilo estilo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
			estilo = cadastroEstiloService.salvar(estilo);
			return ResponseEntity.ok(estilo);
	}
	
	@GetMapping
	public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		
		ModelAndView mv = new ModelAndView("estilos/PesquisaEstilos");
		PageWrapper<Estilo> paginaWrapper = new PageWrapper<>(estilos.filtrar(estiloFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);

		return mv;
		
	}
	
}
