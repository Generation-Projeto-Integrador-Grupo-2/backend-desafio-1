package com.generation.projetointegradordesafio1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.projetointegradordesafio1.model.Colaboradores;

public interface ColaboradoresRepository extends JpaRepository<Colaboradores, Long>{
	
	public List<Colaboradores> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}

