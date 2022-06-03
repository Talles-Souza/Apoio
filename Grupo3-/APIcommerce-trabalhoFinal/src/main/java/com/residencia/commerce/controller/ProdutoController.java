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

import com.residencia.commerce.dto.ProdutoDTO;
import com.residencia.commerce.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAllProduto(){
        List<ProdutoDTO> produtoList = produtoService.findAllProduto();
        return new ResponseEntity<>(produtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer id){
        ProdutoDTO produtoDTO = produtoService.findProdutoById(id);
        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
    }
@PostMapping
    public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO){
        ProdutoDTO novoProduto = produtoService.saveProduto(produtoDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody @Valid ProdutoDTO produtoDTO){
        ProdutoDTO produtoAtualizado = produtoService.updateProduto(produtoDTO);
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Integer id){
        produtoService.deleteProdutoById(id);
        return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
    }
}
