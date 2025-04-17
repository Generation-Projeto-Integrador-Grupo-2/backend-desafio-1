package com.generation.projetointegradordesafio1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projetointegradordesafio1.model.Departamentos;
import com.generation.projetointegradordesafio1.repository.DepartamentosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartamentosController {

	@Autowired
	private DepartamentosRepository departamentosRepository;
	
	@GetMapping
	public ResponseEntity<List<Departamentos>> getAll() {
		return ResponseEntity.ok(departamentosRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Departamentos> getById(@PathVariable Long id) {
		return departamentosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/cargo/{cargo}")
	public ResponseEntity<List<Departamentos>> getByCargo(@PathVariable String cargo) {
		return ResponseEntity
				.ok(departamentosRepository.findAllByCargoContainingIgnoreCase(cargo));
	}
	@PostMapping
	public ResponseEntity<Departamentos> post(@Valid @RequestBody Departamentos departamentos) {
		return ResponseEntity
				.status(HttpStatus.CREATED).body(departamentosRepository.save(departamentos));
	}
	@PutMapping
	public ResponseEntity<Departamentos> put(@Valid @RequestBody Departamentos departamentos) {
		return departamentosRepository.findById(departamentos.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
						.body(departamentosRepository.save(departamentos)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Departamentos> departamentos = departamentosRepository.findById(id);
		
		if(departamentos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		departamentosRepository.deleteById(id);
	}
}
