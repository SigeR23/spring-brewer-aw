package com.siger.brewer.repository.helper.estilo;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.siger.brewer.modal.Estilo;
import com.siger.brewer.repository.filter.EstiloFilter;
import com.siger.brewer.repository.paginacao.PaginacaoUtil;

public class EstilosImpl implements EstilosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);

		paginacaoUtil.preparar(criteria, pageable);
		aplicarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	
	private Long total(EstiloFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		aplicarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void aplicarFiltro(EstiloFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}
	

}
