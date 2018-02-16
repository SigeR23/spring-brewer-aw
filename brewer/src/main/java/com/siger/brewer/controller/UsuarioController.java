package com.siger.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.modal.Usuario;

@Controller
public class UsuarioController {
	
	@RequestMapping("/usuario/novo")
	public String novoUsuario(Usuario Usuario) {
		
		return "usuario/CadastroUsuario";
	}
	
	@RequestMapping(value="/usuario/novo", method = RequestMethod.POST)
	public String cadastrarUsuario(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(usuario);
			return novoUsuario(usuario);
		}
		attributes.addFlashAttribute("mensagem", "Usuario Salvo com sucesso");
		return "redirect:/usuaio/novo";
	}

}
