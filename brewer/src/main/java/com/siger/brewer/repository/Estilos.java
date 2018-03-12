package com.siger.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siger.brewer.modal.Estilo;
import com.siger.brewer.repository.helper.estilo.EstilosQueries;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries{
	
	public Optional<Estilo> findByNomeIgnoreCase(String nome);
}
