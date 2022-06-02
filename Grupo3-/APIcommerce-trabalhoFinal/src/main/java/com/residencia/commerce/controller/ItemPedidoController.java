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

import com.residencia.commerce.entity.ItemPedido;
import com.residencia.commerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> findAllItemPedido() {
		List<ItemPedido> itemPedidoList = itemPedidoService.findAllItemPedido();
		return new ResponseEntity<>(itemPedidoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> findItemPedidoById(@PathVariable Integer id) {
		ItemPedido itemPedido = itemPedidoService.findItemPedidoById(id);
		return new ResponseEntity<>(itemPedido, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody @Valid ItemPedido itempedido) {
		ItemPedido novoItemPedido = itemPedidoService.saveItemPedido(itempedido);
		return new ResponseEntity<>(novoItemPedido, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ItemPedido> updateItemPedido(@RequestBody @Valid ItemPedido itemPedido) {
		ItemPedido itemPedidoAtualizado = itemPedidoService.saveItemPedido(itemPedido);
		return new ResponseEntity<>(itemPedidoAtualizado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemPedido(@PathVariable Integer id) {
		itemPedidoService.deleteItemPedidoById(id);
		return new ResponseEntity<>("Item do pedido deletado com sucesso", HttpStatus.OK);
	}

}
