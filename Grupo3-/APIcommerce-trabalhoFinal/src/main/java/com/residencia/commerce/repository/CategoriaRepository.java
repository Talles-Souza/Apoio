package com.residencia.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.commerce.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
