package com.residencia.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.commerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	Boolean existsByDescricao (String descricao);
	Boolean existsByNomeImagem(String nomeImagem);
}
