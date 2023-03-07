package com.ProjetoNarah.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ProjetoNarah.brewer.model.Cliente;
import com.ProjetoNarah.brewer.model.TipoPessoa;
import com.ProjetoNarah.brewer.repository.Estados;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private Estados estados;

	@RequestMapping("/novo")
	public ModelAndView novo (Cliente cliente) {
		ModelAndView mv = new ModelAndView("clientes/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
			return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		
		return new ModelAndView("redirect:/clientes/novo");
	}
	
}
