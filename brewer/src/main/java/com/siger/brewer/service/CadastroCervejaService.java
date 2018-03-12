package com.siger.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siger.brewer.modal.Cerveja;
import com.siger.brewer.repository.Cervejas;
import com.siger.brewer.service.event.cerveja.CervejaSalvaEvento;

@Service
public class CadastroCervejaService {

	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		
		cervejas.save(cerveja);
		
		//publicando evento quando a cerveja for salva
		publisher.publishEvent(new CervejaSalvaEvento(cerveja));
	}
}
