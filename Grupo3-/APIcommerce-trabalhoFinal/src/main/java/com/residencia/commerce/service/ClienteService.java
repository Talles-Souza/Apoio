package com.residencia.commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.entity.Cliente;
import com.residencia.commerce.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	public List<Cliente> findAllCliente() {
		return clienteRepository.findAll();
	}

	public Cliente findClienteById(Integer id) {
		return clienteRepository.findById(id).isPresent() ? clienteRepository.findById(id).get() : null;
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void deleteClienteById(Integer id) {
		Cliente cliente = clienteRepository.findById(id).get();
		clienteRepository.delete(cliente); 
	}
}
