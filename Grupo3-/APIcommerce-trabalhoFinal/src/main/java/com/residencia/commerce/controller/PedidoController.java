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

import com.residencia.commerce.dto.PedidoDTO;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	 @Operation(summary="Listar todos os pedidos", description  = "Listagem de pedidos")
		@ApiResponses(value = { 
				  @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
				  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
				  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
				  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<List<PedidoDTO>> findAllPedido(){
        List<PedidoDTO> pedidoList = pedidoService.findAllPedido();
        return new ResponseEntity<>(pedidoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary="Retornar um pedido", description  = "Pedido")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<PedidoDTO> findPedidoById(@PathVariable Integer id){
    	PedidoDTO pedidoDTO = pedidoService.findPedidoById(id);
    	if (null == pedidoDTO)
			throw new NoSuchElementFoundException("Não foi encontrado Pedido com id: " + id + " pois não existe.");
		else
        return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary="Inserir os dados de pedido", description  = "Pedido adicionado")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<PedidoDTO> savePedido(@RequestBody @Valid PedidoDTO pedidoDTO){
    	PedidoDTO novoPedido = pedidoService.savePedido(pedidoDTO);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary="Atualizar os dados de pedido", description  = "Pedido atualizado")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<PedidoDTO> updatePedido(@RequestBody @Valid PedidoDTO pedidoDTO){
    	PedidoDTO pedidoAtualizado = pedidoService.findPedidoById(pedidoDTO.getIdPedido());
    	if (null == pedidoAtualizado)
			throw new NoSuchElementFoundException("Não foi possivel atualizar Pedido com este id ");
		else
        return new ResponseEntity<>(pedidoService.updatePedido(pedidoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Remover um pedido", description  = "Pedido removido")
   	@ApiResponses(value = { 
   			  @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
   			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
   			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
   			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<String> deletePedido(@PathVariable Integer id){
    	PedidoDTO PedidoAtualizado = pedidoService.findPedidoById(id);
		if (null == PedidoAtualizado)
			throw new NoSuchElementFoundException("Não foi possivel deletar Pedido com id: " + id + " pois não existe.");
		else
			pedidoService.deletePedidoById(id);
		return new ResponseEntity<>("Pedido deletado com sucesso!", HttpStatus.OK);
    }
}
	