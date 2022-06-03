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

import com.residencia.commerce.dto.ClienteDTO;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	 @Operation(summary="Listar todos os clientes", description  = "Listagem de clientes")
		@ApiResponses(value = { 
				  @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
				  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
				  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
				  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<List<ClienteDTO>> findAllCliente(){
        List<ClienteDTO> clienteList = clienteService.findAllCliente();
        return new ResponseEntity<>(clienteList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary="Retornar um cliente", description  = "Cliente")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer id){
        ClienteDTO clienteDTO = clienteService.findClienteById(id);
        if (null == clienteDTO)
            throw new NoSuchElementFoundException("Não foi encontrado cliente com id: " + id + " pois não existe.");
        else
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary="Inserir os dados de cliente", description  = "Cliente adicionado")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        ClienteDTO novoCliente = clienteService.saveCliente(clienteDTO);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary="Atualizar os dados de cliente", description  = "Cliente atualizado")
   	@ApiResponses(value = { 
   			  @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
   			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
   			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
   			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        ClienteDTO clienteAtualizado = clienteService.findClienteById(clienteDTO.getIdCliente());
        if (null == clienteAtualizado)
            throw new NoSuchElementFoundException("Não foi possivel atualizar cliente com este id");
        else
            return new ResponseEntity<>(clienteService.updateCliente(clienteDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Remover um cliente", description  = "Cliente removido")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<String> deleteCliente(@PathVariable Integer id){
        ClienteDTO clienteAtualizado = clienteService.findClienteById(id);
        if (null == clienteAtualizado)
            throw new NoSuchElementFoundException("Não foi possivel deletar cliente com id: " + id + " pois não existe.");
        else
        clienteService.deleteClienteById(id);
        return new ResponseEntity<>("Cliente deletado com sucesso!", HttpStatus.OK);
    }
}
