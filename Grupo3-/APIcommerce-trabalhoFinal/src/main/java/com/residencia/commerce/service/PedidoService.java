package com.residencia.commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.entity.Pedido;
import com.residencia.commerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<Pedido> findAllPedido(){
		return pedidoRepository.findAll();
	}
	
	public Pedido findPedidoById(Integer id) {
		return pedidoRepository.findById(id).isPresent() ?
				pedidoRepository.findById(id).get() : null;
	}
	
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido updatePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public void deletePedidoById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedidoRepository.delete(pedido);
	}
}
