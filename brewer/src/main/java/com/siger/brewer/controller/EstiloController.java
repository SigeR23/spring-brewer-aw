package com.siger.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.brewer.controller.page.PageWrapper;
import com.siger.brewer.modal.Cerveja;
import com.siger.brewer.modal.Estilo;
import com.siger.brewer.repository.Estilos;
import com.siger.brewer.repository.filter.EstiloFilter;
import com.siger.brewer.service.CadastroEstiloService;
import com.siger.brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping("/estilo")
public class EstiloController {
	
	@Autowired
	private CadastroEstiloService cadastroEstilo;
	
	@Autowired
	private Estilos estilos;
	
	@RequestMapping("/novo")
	public ModelAndView novoEstilo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("/estilo/CadastroEstilo");
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView CadastrarEstilo(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(estilo);
			return novoEstilo(estilo);
		}
		try {
			cadastroEstilo.salvar(estilo);
			
		} catch(NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novoEstilo(estilo);
		}
		
		attributes.addFlashAttribute("mensagem", "estilo salvo com sucesso");
		return new ModelAndView("redirect:/estilo/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
		 if(result.hasErrors()) {
			 return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
		 }
		 estilo = cadastroEstilo.salvar(estilo);
			 
		 return ResponseEntity.ok(estilo);
	}
	
	@GetMapping
	public ModelAndView pesquisar(EstiloFilter filtro, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest ) {
		ModelAndView mv = new ModelAndView("/estilo/PesquisarEstilo");
		
		PageWrapper<Estilo> paginaWrapper = new PageWrapper<>(estilos.filtrar(filtro, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
}
