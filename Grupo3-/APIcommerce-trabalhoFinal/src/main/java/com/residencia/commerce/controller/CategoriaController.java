package com.residencia.commerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.commerce.entity.Categoria;
import com.residencia.commerce.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategoria() {
		List<Categoria> categoriaList = categoriaService.findAllCategoria();
		return new ResponseEntity<>(categoriaList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findCategoriaById(id);
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	@PostMapping
	@Valid
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		Categoria novaCategoria = categoriaService.saveCategoria(categoria);
		return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
	}

	@PutMapping
	@Valid
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria) {
		Categoria categoriaAtualizada = categoriaService.updateCategoria(categoria);
		return new ResponseEntity<>(categoriaAtualizada, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoriaById(@PathVariable Integer id) {
		categoriaService.deleteCategoriaById(id);
		return new ResponseEntity<>("Categoria deletada com sucesso", HttpStatus.OK);
	}
	
}
