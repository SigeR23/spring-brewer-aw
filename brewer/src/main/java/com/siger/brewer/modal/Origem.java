package com.siger.brewer.modal;

public enum Origem {

	NACIONAL("Nacional"),
	INTERNCIONAL("Internacional");
	
	private String descricao;
	
	Origem(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
} 
