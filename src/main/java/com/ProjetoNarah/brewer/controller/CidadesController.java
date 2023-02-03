package com.ProjetoNarah.brewer.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProjetoNarah.brewer.model.Cidade;
import com.ProjetoNarah.brewer.repository.Cidades;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private Cidades cidades;

	@RequestMapping(value ="/novo", method = RequestMethod.GET)
	public String novo(Cidade cidade) {
		return "cidade/CadastroCidades";
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(Long codigoEstado){
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
	public String cadastrar(@Valid Cidade cidade, BindingResult result,RedirectAttributes attributes ) {
		if(result.hasErrors()) {
			return novo(cidade);
		}
		return null;
	}
}

