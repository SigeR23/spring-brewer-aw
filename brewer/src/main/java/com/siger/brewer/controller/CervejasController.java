package com.siger.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.controller.page.PageWrapper;
import com.siger.brewer.modal.Cerveja;
import com.siger.brewer.modal.Origem;
import com.siger.brewer.modal.Sabor;
import com.siger.brewer.repository.Cervejas;
import com.siger.brewer.repository.Estilos;
import com.siger.brewer.repository.filter.CervejaFilter;
import com.siger.brewer.service.CadastroCervejaService;

@Controller
@RequestMapping("/cerveja")
public class CervejasController {

	@Autowired
	private Estilos estilos;

	@Autowired
	private CadastroCervejaService cadastroCervejaService;

	@Autowired
	private Cervejas cervejas;

	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {

		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {

			model.addAttribute(cerveja);
			return novo(cerveja);
		}

		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja Salva com sucesso");

		return new ModelAndView("redirect:/cerveja/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCerveja");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		PageWrapper<Cerveja> paginaWrapper = new PageWrapper<>(cervejas.filtrar(cervejaFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}
