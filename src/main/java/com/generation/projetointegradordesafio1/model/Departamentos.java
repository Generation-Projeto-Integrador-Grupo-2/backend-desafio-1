package com.generation.projetointegradordesafio1.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "tb_departamentos")
public class Departamentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 100)
	private String cargo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamentos", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("departamentos")
	private List<Colaboradores> colaboradores;
	
	@NotBlank
	@Size(min = 1, max = 100)
	private String nomeDepartamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Colaboradores> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaboradores> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}
}

