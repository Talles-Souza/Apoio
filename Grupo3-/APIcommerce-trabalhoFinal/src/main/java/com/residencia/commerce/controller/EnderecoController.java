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

import com.residencia.commerce.dto.EnderecoDTO;
import com.residencia.commerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAllEndereco(){
        List<EnderecoDTO> enderecoList = enderecoService.findAllEndereco();
        return new ResponseEntity<>(enderecoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer id){
    	EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);
        return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody EnderecoDTO enderecoDTO){
    	EnderecoDTO novoEndereco = enderecoService.saveEndereco(enderecoDTO);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO){
    	EnderecoDTO enderecoAtualizado = enderecoService.updateEndereco(enderecoDTO);
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id){
		enderecoService.deleteEnderecoById(id);
		return new ResponseEntity<>("Endereço deletado com sucesso", HttpStatus.OK);
	}
}
