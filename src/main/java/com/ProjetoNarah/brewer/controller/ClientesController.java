package com.ProjetoNarah.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping("novo")
	public ModelAndView novo (Cliente cliente) {
		ModelAndView mv = new ModelAndView("clientes/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
			return mv;
	}
	
//	@RequestMapping(value ="/clientes/novo", method= RequestMethod.POST)
//	public String cadastrar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
//		if(result.hasErrors()) {
//			return novo(cliente);
//		}
//		
//		//salvar no banco de dados ..
//		
//		attributes.addFlashAttribute("mensagem","cliente cadastrado com sucesso");
//		System.out.println(">>> nome: " + cliente.getNome());
//		return "redirect:/clientes/novo";
//	}
	
	
}
