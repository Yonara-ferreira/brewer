package com.ProjetoNarah.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProjetoNarah.brewer.model.Estilo;

@Controller
public class EstilosController {

	@RequestMapping(value = "/estilos/novo", method = RequestMethod.GET)	
	public String novo(Estilo estilo) {
		return "estilos/CadastroEstilos";
		
	}
	
	@RequestMapping(value = "/estilos/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo (estilo);
			
		}
		return null;
	}
}
