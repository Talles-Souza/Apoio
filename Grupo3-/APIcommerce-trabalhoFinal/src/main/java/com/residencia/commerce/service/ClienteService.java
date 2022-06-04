	package com.residencia.commerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.dto.ClienteDTO;
import com.residencia.commerce.entity.Cliente;
import com.residencia.commerce.entity.Endereco;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoService enderecoService;

	public List<ClienteDTO> findAllCliente() {
		List<Cliente> listaClienteEntity = clienteRepository.findAll();
		List<ClienteDTO> listaClienteDTO = new ArrayList<>();

		for (Cliente cliente : listaClienteEntity) {
			listaClienteDTO.add(converterEntityToDTO(cliente));
		}

		return listaClienteDTO;
	}

	public ClienteDTO findClienteById(Integer id) {
		return clienteRepository.findById(id).isPresent() ? converterEntityToDTO(clienteRepository.findById(id).get())
				: null;
	}

	public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByCpfCliente(clienteDTO.getCpfCliente())) {
            throw new NoSuchElementFoundException("CPF já cadastrado");
        }else if(clienteRepository.existsByEmailCliente(clienteDTO.getEmailCliente())) {
            throw new NoSuchElementFoundException("Email já cadastrado");
        }Cliente cliente = clienteRepository.save(ConverteDTOToEntidade(clienteDTO));
    return converterEntityToDTO(cliente);
}

	public ClienteDTO updateCliente(ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.save(ConverteDTOToEntidade(clienteDTO));
		return converterEntityToDTO(cliente);
	}

	public void deleteClienteById(Integer id) {
		Cliente cliente = clienteRepository.findById(id).get();
		clienteRepository.delete(cliente);
	}

	public Cliente ConverteDTOToEntidade(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();

		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setEmailCliente(clienteDTO.getEmailCliente());
		cliente.setNomeCompletoCliente(clienteDTO.getNomeCompletoCliente());
		cliente.setCpfCliente(clienteDTO.getCpfCliente());
		cliente.setTelefoneCliente(clienteDTO.getTelefoneCliente());
		cliente.setDataNascimentoCliente(clienteDTO.getDataNascimentoCliente());
		/*
		endereco = enderecoService
				.ConverteDTOToEntidade(enderecoService.findEnderecoById(clienteDTO.getEnderecoDTO().getIdEndereco()));
		cliente.setEndereco(endereco);
*/
		return cliente;
	}

	public ClienteDTO converterEntityToDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();

		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setEmailCliente(cliente.getEmailCliente());
		clienteDTO.setNomeCompletoCliente(cliente.getNomeCompletoCliente());
		clienteDTO.setCpfCliente(cliente.getCpfCliente());
		clienteDTO.setTelefoneCliente(cliente.getTelefoneCliente());
		clienteDTO.setDataNascimentoCliente(cliente.getDataNascimentoCliente());

		return clienteDTO;
	}
}
