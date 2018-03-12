package com.siger.brewer.repository.helper.estilo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.siger.brewer.modal.Estilo;
import com.siger.brewer.repository.filter.EstiloFilter;


public interface EstilosQueries {
	
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
}
