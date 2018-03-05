package com.siger.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siger.brewer.modal.Estilo;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long> {
	
	public Optional<Estilo> findByNomeIgnoreCase(String nome);
}
