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

import com.residencia.commerce.dto.EnderecoDTO;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAllEndereco() {
        List<EnderecoDTO> enderecoList = enderecoService.findAllEndereco();
        return new ResponseEntity<>(enderecoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer id) {
        EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);
        if (null == enderecoDTO)
            throw new NoSuchElementFoundException("Não foi encontrado o endereço com o id " + id);
        else
            return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO novoEndereco = enderecoService.saveEndereco(enderecoDTO);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoAtualizado = enderecoService.findEnderecoById(enderecoDTO.getIdEndereco());
        if (null == enderecoAtualizado)
            throw new NoSuchElementFoundException(
                    "Não foi possivel atualizar o endereço com o id " + enderecoDTO.getIdEndereco());
        else
            return new ResponseEntity<>(enderecoService.updateEndereco(enderecoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) {
        EnderecoDTO enderecoAtualizado = enderecoService.findEnderecoById(id);
        if (null == enderecoAtualizado)
            throw new NoSuchElementFoundException("Não foi possivel atualizar o endereço com o id " + id);
        else
            enderecoService.deleteEnderecoById(id);
        return new ResponseEntity<>("Endereço deletado com sucesso", HttpStatus.OK);
    }
}
