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

import com.residencia.commerce.dto.ItemPedidoDTO;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {
	//falta enviar email com o formato de nota fiscal
	@Autowired
	 ItemPedidoService itemPedidoService;
	
	@GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> findAllItemPedido(){
        List<ItemPedidoDTO> itemPedidoList = itemPedidoService.findAllItemPedido();
        return new ResponseEntity<>(itemPedidoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> findItemPedidoById(@PathVariable Integer id){
    	ItemPedidoDTO itemPedidoDTO = itemPedidoService.findItemPedidoById(id);
    	if (null == itemPedidoDTO)
			throw new NoSuchElementFoundException("Não foi encontrado um ItemPedido com o id " + id);
		else
        return new ResponseEntity<>(itemPedidoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> saveItemPedido(@RequestBody @Valid ItemPedidoDTO itemPedidoDTO){
    	ItemPedidoDTO novoItemPedido = itemPedidoService.saveItemPedido(itemPedidoDTO);
    	return new ResponseEntity<>(novoItemPedido, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemPedidoDTO> updateItemPedido(@RequestBody @Valid ItemPedidoDTO itemPedidoDTO){
    	ItemPedidoDTO itemPedidoAtualizado = itemPedidoService.findItemPedidoById(itemPedidoDTO.getIdItemPedido());
    	if (null == itemPedidoAtualizado)
			throw new NoSuchElementFoundException("Não foi encontrado um ItemPedido com esse id" + itemPedidoDTO.getIdItemPedido());
		else
    	return new ResponseEntity<>(itemPedidoService.updateItemPedido(itemPedidoDTO), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemPedido(@PathVariable Integer id){
		ItemPedidoDTO itemPedidoAtualizado = itemPedidoService.findItemPedidoById(id);
		if (null == itemPedidoAtualizado)
			throw new NoSuchElementFoundException("Não foi encontrado um ItemPedido com o id " + id);
		else
			itemPedidoService.deleteItemPedidoById(id);
		return new ResponseEntity<>("Item do pedido deletado com sucesso", HttpStatus.OK);
	}

}
