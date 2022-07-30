package com.ProjetoNarah.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProjetoNarah.brewer.model.Cidade;

@Controller
public class CidadesController {

	@RequestMapping(value ="/cidades/novo", method = RequestMethod.GET)
	public String novo(Cidade cidade) {
		return "cidade/CadastroCidades";
	}
	
	public String cadastrar(@Valid Cidade cidade, BindingResult result,RedirectAttributes attributes ) {
		if(result.hasErrors()) {
			return novo(cidade);
		}
		return null;
	}
}

