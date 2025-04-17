package com.generation.projetointegradordesafio1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.projetointegradordesafio1.model.Departamentos;

public interface DepartamentosRepository extends JpaRepository<Departamentos, Long> {
	
	public List<Departamentos> findAllByCargoContainingIgnoreCase(@Param("cargo") String cargo);
}

