package com.residencia.commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.dto.ProdutoDTO;
import com.residencia.commerce.entity.Produto;
import com.residencia.commerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAllProduto() {
		return produtoRepository.findAll();
	}

	public Produto findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}

	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deleteProdutoById(Integer id) {
		Produto produto = produtoRepository.findById(id).get();
		produtoRepository.delete(produto);

	}

	private Produto ConverteDTOToEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();

		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.getCategoria().setIdCategoria(produtoDTO.getCategoriaDTO().getIdCategoria());
		produto.setDataCadastroProduto(produtoDTO.getDataCadastroProduto());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setNomeImagemProduto(produtoDTO.getNomeImagemProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQtdEstoqueProduto(produtoDTO.getQtdEstoqueProduto());
		produto.setValorUnitarioProduto(produtoDTO.getValorUnitarioProduto());

		return produto;
	}

	private ProdutoDTO converterEntityToDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.getCategoriaDTO().setIdCategoria(produto.getCategoria().getIdCategoria());
		produtoDTO.setDataCadastroProduto(produto.getDataCadastroProduto());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeImagemProduto(produto.getNomeImagemProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQtdEstoqueProduto(produto.getQtdEstoqueProduto());
		produtoDTO.setValorUnitarioProduto(produto.getValorUnitarioProduto());

		return produtoDTO;

	}

}
