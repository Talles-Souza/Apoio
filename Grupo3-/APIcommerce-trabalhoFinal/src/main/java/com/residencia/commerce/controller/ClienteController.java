package com.residencia.commerce.controller;

import java.util.List;

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

import com.residencia.commerce.dto.ClienteDTO;
import com.residencia.commerce.entity.Cliente;
import com.residencia.commerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
    public ResponseEntity<List<ClienteDTO>> findAllCliente(){
        List<ClienteDTO> clienteList = clienteService.findAllCliente();
        return new ResponseEntity<>(clienteList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer id){
    	ClienteDTO clienteDTO = clienteService.findClienteById(id);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO){
    	ClienteDTO novoCliente = clienteService.saveCliente(clienteDTO);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO){
    	ClienteDTO clienteAtualizado = clienteService.updateCliente(clienteDTO);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Integer id){
    	clienteService.deleteClienteById(id);
        return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
    }
}
