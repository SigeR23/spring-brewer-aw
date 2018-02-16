package com.siger.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.modal.Cidade;

@Controller
public class CidadeController {

	@RequestMapping("/cidade/novo")
	public String novoCidade(Cidade cidade) {
		
		return "cidade/CadastroCidade";
	}
	
	@RequestMapping(value="/cidade/novo", method = RequestMethod.POST)
	public String cadastrarCidade(@Valid Cidade cidade, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(cidade);
			return novoCidade(cidade);
		}
		attributes.addFlashAttribute("mensagem", "Cidade Salva com Sucesso");
		return "redirect:/cidade/novo";
	}

}
