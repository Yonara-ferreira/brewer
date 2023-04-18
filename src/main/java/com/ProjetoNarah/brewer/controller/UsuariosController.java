package com.ProjetoNarah.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProjetoNarah.brewer.model.Usuario;
import com.ProjetoNarah.brewer.service.CadastroUsuarioService;
import com.ProjetoNarah.brewer.service.exception.EmailUsuarioJaCadastradoException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) throws EmailUsuarioJaCadastradoException {
		if(result.hasErrors()) {
			return novo(usuario);
		}
		try {
			cadastroUsuarioService.salvar(usuario);
			attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		}catch(EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		return new ModelAndView("redirect:/usuarios/novo");
	}

}
