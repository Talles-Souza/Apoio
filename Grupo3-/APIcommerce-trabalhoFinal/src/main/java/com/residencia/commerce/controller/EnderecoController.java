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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
	@Operation(summary="Listar todos os endereços", description  = "Listagem de endereços")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<List<EnderecoDTO>> findAllEndereco() {
        List<EnderecoDTO> enderecoList = enderecoService.findAllEndereco();
        return new ResponseEntity<>(enderecoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary="Retornar um endereço", description  = "Endereço")
   	@ApiResponses(value = { 
   			  @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
   			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
   			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
   			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer id) {
        EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);
        if (null == enderecoDTO)
            throw new NoSuchElementFoundException("Não foi encontrado endereço com id: " + id + " pois não existe.");
        else
            return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary="Inserir os dados de endereço", description  = "Endereço adicionado")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO novoEndereco = enderecoService.saveEndereco(enderecoDTO);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary="Atualizar os dados de endereço", description  = "Endereço atualizado")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoAtualizado = enderecoService.findEnderecoById(enderecoDTO.getIdEndereco());
        if (null == enderecoAtualizado)
            throw new NoSuchElementFoundException(
                    "Não foi possivel atualizar endereço com este id");
        else
            return new ResponseEntity<>(enderecoService.updateEndereco(enderecoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Remover um endereço", description  = "Endereço removido")
   	@ApiResponses(value = { 
   			  @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
   			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
   			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
   			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) {
        EnderecoDTO enderecoAtualizado = enderecoService.findEnderecoById(id);
        if (null == enderecoAtualizado)
            throw new NoSuchElementFoundException("Não foi possivel deletar endereço com id: " + id + " pois não existe.");
        else
            enderecoService.deleteEnderecoById(id);
        return new ResponseEntity<>("Endereço deletado com sucesso!", HttpStatus.OK);
    }
}
