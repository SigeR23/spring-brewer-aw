package com.siger.brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.modal.Cerveja;

@Controller
public class CervejasController {
	
	private static Logger logger = LoggerFactory.getLogger(CervejasController.class);
	
	@RequestMapping("/cerveja/novo")
	public String novo(Cerveja cerveja) {
		logger.error("logger nivel erro");
		logger.info("logger nivel info");
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cerveja/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			model.addAttribute(cerveja);
			return novo(cerveja);
		}
		
		attributes.addFlashAttribute("mensagem", "Cerveja Salva com sucesso");
		return "redirect:/cerveja/novo";
	}
	
}
