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

import com.residencia.commerce.dto.ItemPedidoDTO;
import com.residencia.commerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {
	
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
        return new ResponseEntity<>(itemPedidoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> saveItemPedido(@RequestBody ItemPedidoDTO itemPedidoDTO){
    	ItemPedidoDTO novoItemPedido = itemPedidoService.saveItemPedido(itemPedidoDTO);
        return new ResponseEntity<>(novoItemPedido, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemPedidoDTO> updateItemPedido(@RequestBody ItemPedidoDTO itemPedidoDTO){
    	ItemPedidoDTO itemPedidoAtualizado = itemPedidoService.updateItemPedido(itemPedidoDTO);
        return new ResponseEntity<>(itemPedidoAtualizado, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemPedido(@PathVariable Integer id){
		itemPedidoService.deleteItemPedidoById(id);
		return new ResponseEntity<>("Item do pedido deletado com sucesso", HttpStatus.OK);
	}

}
