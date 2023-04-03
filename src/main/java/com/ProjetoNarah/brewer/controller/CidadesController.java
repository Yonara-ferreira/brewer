package com.ProjetoNarah.brewer.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ProjetoNarah.brewer.controller.page.PageWrapper;
import com.ProjetoNarah.brewer.model.Cidade;
import com.ProjetoNarah.brewer.repository.Cidades;
import com.ProjetoNarah.brewer.repository.Estados;
import com.ProjetoNarah.brewer.repository.filter.CidadeFilter;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private Cidades cidades;
	
	@Autowired
	private Estados estados;

	@RequestMapping(value ="/novo", method = RequestMethod.GET)
	public String novo(Cidade cidade) {
		return "cidade/CadastroCidades";
	}
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {  }
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidades");
		mv.addObject("estados", estados.findAll());

		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidades.filtrar(cidadeFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);

		return mv;
	}
	
	
}

