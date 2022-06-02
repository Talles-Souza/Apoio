package com.residencia.commerce.controller;

import java.util.List;

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

import com.residencia.commerce.entity.Produto;
import com.residencia.commerce.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto(){
		List<Produto> produtoList = produtoService.findAllProduto();
		return new ResponseEntity<>(produtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findProdutoById(Integer id){
		Produto produto = produtoService.findProdutoById(id);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto){
		Produto novoProduto = produtoService.saveProduto(produto);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto){
		Produto produtoAtualizado = produtoService.updateProduto(produto);
		return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduto(@PathVariable Integer id){
		produtoService.deleteProdutoById(id);
		return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
	}
}
