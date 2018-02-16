package com.siger.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.modal.Estilo;

@Controller
public class EstiloController {

	@RequestMapping("estilo/novo")
	public String novoEstilo(Estilo estilo) {
		return "/estilo/CadastroEstilo";
	}
	
	@RequestMapping(value = "estilo/novo", method = RequestMethod.POST)
	public String CadastrarEstilo(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(estilo);
			return novoEstilo(estilo);
		}
		attributes.addFlashAttribute("mensagem", "estilo salvo com sucesso");
		return "redirect:/estilo/novo";
	}
}
