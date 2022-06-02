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

import com.residencia.commerce.entity.Cliente;
import com.residencia.commerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAllCliente() {
		List<Cliente> clienteList = clienteService.findAllCliente();
		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id){
		Cliente cliente = clienteService.findClienteById(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@PostMapping
	@Valid
	public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid Cliente cliente){
		Cliente novoCliente = clienteService.saveCliente(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}
	
	@PutMapping
	@Valid
	public ResponseEntity<Cliente> updateCliente(@RequestBody @Valid Cliente cliente){
		Cliente clienteAtualizado = clienteService.saveCliente(cliente);
		return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClienteById(@PathVariable Integer id){
		clienteService.deleteClienteById(id);
		return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
	}
}
