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

import com.residencia.commerce.entity.Endereco;
import com.residencia.commerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAllEndereco(){
		List<Endereco> enderecoList = enderecoService.findAllEndereco();
		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findEnderecoById(@PathVariable Integer id){
		Endereco endereco = enderecoService.findEnderecoById(id);
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}
	
	@PostMapping
	@Valid
	public ResponseEntity<Endereco> saveEndereco(@RequestBody @Valid Endereco endereco){
		Endereco novoEndereco = enderecoService.saveEndereco(endereco);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
	}
	
	@PutMapping
	@Valid
	public ResponseEntity<Endereco> updateEndereco(@RequestBody @Valid Endereco endereco){
		Endereco enderecoAtualizado = enderecoService.saveEndereco(endereco);
		return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id){
		enderecoService.deleteEnderecoById(id);
		return new ResponseEntity<>("Endereço deletado com sucesso", HttpStatus.OK);
	}
}
