package com.siger.brewer.repository.helper.cerveja;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.siger.brewer.modal.Cerveja;
import com.siger.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {
	
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
}
