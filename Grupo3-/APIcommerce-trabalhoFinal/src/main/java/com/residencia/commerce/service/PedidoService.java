package com.residencia.commerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.dto.ClienteDTO;
import com.residencia.commerce.dto.PedidoDTO;
import com.residencia.commerce.entity.Cliente;
import com.residencia.commerce.entity.Pedido;
import com.residencia.commerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteService clienteService;

	public List<PedidoDTO> findAllPedido() {
		List<Pedido> listaPedidosEntity = pedidoRepository.findAll();
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();

		for (Pedido pedido : listaPedidosEntity) {
			listaPedidosDTO.add(converterEntityToDTO(pedido));
		}

		return listaPedidosDTO;
	}

	public PedidoDTO findPedidoById(Integer id) {
		return pedidoRepository.findById(id).isPresent() ? converterEntityToDTO(pedidoRepository.findById(id).get())
				: null;
	}

	public PedidoDTO savePedido(PedidoDTO pedidoDTO) {
		Date data = new Date();
		pedidoDTO.setDataPedido(data);
		Pedido pedido = pedidoRepository.save(ConverteDTOToEntidade(pedidoDTO));
		return converterEntityToDTO(pedido);
	}

	public PedidoDTO updatePedido(PedidoDTO pedidoDTO) {
		Pedido pedido = pedidoRepository.save(ConverteDTOToEntidade(pedidoDTO));
		return converterEntityToDTO(pedido);
	}

	public void deletePedidoById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedidoRepository.delete(pedido);

	}

	private Pedido ConverteDTOToEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();

		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setDataEntregaPedido(pedidoDTO.getDataEntregaPedido());
		pedido.setDataEnvioPedido(pedidoDTO.getDataEnvioPedido());
		pedido.setStatusPedido(pedidoDTO.getStatusPedido());
		//pedido.getCliente().setIdCliente(pedidoDTO.getClienteDTO().getIdCliente());
		
		cliente = clienteService.ConverteDTOToEntidade(clienteService.findClienteById(pedidoDTO.getClienteDTO().getIdCliente()));
		pedido.setCliente(cliente);
		
		
		return pedido;
	};

	private PedidoDTO converterEntityToDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setDataEntregaPedido(pedido.getDataEntregaPedido());
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataEnvioPedido(pedido.getDataEnvioPedido());
		pedidoDTO.setStatusPedido(pedido.getStatusPedido());
	//	pedidoDTO.getClienteDTO().setIdCliente(pedido.getCliente().getIdCliente());

		return pedidoDTO;

	}
}
