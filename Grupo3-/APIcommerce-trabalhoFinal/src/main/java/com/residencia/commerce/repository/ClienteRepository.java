package com.residencia.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.commerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
	Boolean existsByCpf (String cpf); 
	Boolean existsByEmail (String email); 
}
