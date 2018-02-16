package com.siger.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.modal.Cliente;

@Controller
public class ClienteController {
	
	@RequestMapping("/cliente/novo")
	public String novoCliente(Cliente cliente) {
		
		return "cliente/CadastroCliente";
	}
	
	@RequestMapping(value="/cliente/novo", method = RequestMethod.POST)
	public String cadastrarCliente(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(cliente);
			return novoCliente(cliente);
		}
		attributes.addFlashAttribute("mensagem", "Cliente Salvo com sucesso");
		return "redirect:/cliente/novo";
	}
}
