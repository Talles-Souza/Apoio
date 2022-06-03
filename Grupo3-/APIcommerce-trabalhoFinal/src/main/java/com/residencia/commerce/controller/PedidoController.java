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
import com.residencia.commerce.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
    public ResponseEntity<List<PedidoDTO>> findAllPedido(){
        List<PedidoDTO> pedidoList = pedidoService.findAllPedido();
        return new ResponseEntity<>(pedidoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findPedidoById(@PathVariable Integer id){
    	PedidoDTO pedidoDTO = pedidoService.findPedidoById(id);
        return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> savePedido(@RequestBody @Valid PedidoDTO pedidoDTO){
    	PedidoDTO novoPedido = pedidoService.savePedido(pedidoDTO);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PedidoDTO> updatePedido(@RequestBody @Valid PedidoDTO pedidoDTO){
    	PedidoDTO pedidoAtualizado = pedidoService.updatePedido(pedidoDTO);
        return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Integer id){
    	pedidoService.deletePedidoById(id);
        return new ResponseEntity<>("Pedido deletado com sucesso", HttpStatus.OK);
    }
}
	