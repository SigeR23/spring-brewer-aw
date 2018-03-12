package com.siger.brewer.service.event.cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.siger.brewer.modal.Cerveja;

public class CervejaSalvaEvento {
	
	@Autowired
	private Cerveja cerveja;
	
	public CervejaSalvaEvento(Cerveja cerveja) {
		this.cerveja = cerveja;
		
	}
	
	public Cerveja getCerveja() {
		return cerveja;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(cerveja.getFoto());
	}
}
