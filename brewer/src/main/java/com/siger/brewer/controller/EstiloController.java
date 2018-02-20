package com.siger.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.modal.Estilo;
import com.siger.brewer.service.CadastroEstiloService;

@Controller
public class EstiloController {
	
	@Autowired
	private CadastroEstiloService cadastroEstilo;
	
	@RequestMapping("estilo/novo")
	public ModelAndView novoEstilo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("/estilo/CadastroEstilo");
		return mv;
	}
	
	@RequestMapping(value = "estilo/novo", method = RequestMethod.POST)
	public ModelAndView CadastrarEstilo(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(estilo);
			return novoEstilo(estilo);
		}
		cadastroEstilo.salvar(estilo);
		attributes.addFlashAttribute("mensagem", "estilo salvo com sucesso");
		return new ModelAndView("redirect:/estilo/novo");
	}
}
