package com.residencia.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.commerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
