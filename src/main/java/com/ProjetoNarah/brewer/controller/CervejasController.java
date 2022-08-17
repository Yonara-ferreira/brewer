package com.ProjetoNarah.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProjetoNarah.brewer.model.Cerveja;
import com.ProjetoNarah.brewer.model.Origem;
import com.ProjetoNarah.brewer.model.Sabor;
import com.ProjetoNarah.brewer.repository.Estilos;
import com.ProjetoNarah.brewer.service.CadastroCervejaService;


@Controller
public class CervejasController {
	
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.GET)
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		return mv;
	} 

	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(cerveja);
		}
		
		//salvar no banco de dados... 
		
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		return new ModelAndView ("redirect:/cervejas/novo");
	}
	
}
