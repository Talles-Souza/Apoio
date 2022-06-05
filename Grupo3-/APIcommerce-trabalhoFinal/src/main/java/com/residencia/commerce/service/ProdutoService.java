package com.residencia.commerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.commerce.dto.ProdutoDTO;
import com.residencia.commerce.entity.Categoria;
import com.residencia.commerce.entity.Produto;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	Arquivo2Service arquivoService;

	public List<ProdutoDTO> findAllProduto() {
		List<Produto> listaProdutosEntity = produtoRepository.findAll();
		List<ProdutoDTO> listaProdutosDTO = new ArrayList<>();

		for (Produto produto : listaProdutosEntity) {
			listaProdutosDTO.add(converterEntityToDTO(produto));
		}

		return listaProdutosDTO;
	}

	public ProdutoDTO findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? converterEntityToDTO(produtoRepository.findById(id).get())
				: null;
	}

	public ProdutoDTO saveProduto(ProdutoDTO produtoDTO) {
		if (produtoRepository.existsByDescricao(produtoDTO.getDescricao())) {
			throw new NoSuchElementFoundException("Essa descrição já existe no banco");
		}
		Produto produto = produtoRepository.save(ConverteDTOToEntidade(produtoDTO));
		return converterEntityToDTO(produto);
	}

	public ProdutoDTO updateProduto(ProdutoDTO produtoDTO) {
		Produto produto = produtoRepository.save(ConverteDTOToEntidade(produtoDTO));

		return converterEntityToDTO(produto);
	}

	public void deleteProdutoById(Integer id) {
		Produto produto = produtoRepository.findById(id).get();
		produtoRepository.delete(produto);

	}

	private Produto ConverteDTOToEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();

		produto.setIdProduto(produtoDTO.getIdProduto());
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

		produtoDTO.setDataCadastroProduto(produto.getDataCadastroProduto());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeImagemProduto(produto.getNomeImagemProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQtdEstoqueProduto(produto.getQtdEstoqueProduto());
		produtoDTO.setValorUnitarioProduto(produto.getValorUnitarioProduto());

		return produtoDTO;

	}

	public ProdutoDTO saveProdutoComFoto(String produtoString, MultipartFile file) throws Exception {
		Produto produtoConvertido = new Produto();
		try {
			ObjectMapper objMapper = new ObjectMapper();
			produtoConvertido = objMapper.readValue(produtoString, Produto.class);
		} catch (IOException e) {
			System.out.println("Ocorreu um erro na gravação");
		}
		Boolean nome = conferindoNome(file);
		if (nome == false) {
			Produto produtoBD = produtoRepository.save(produtoConvertido);
			produtoBD.setNomeImagemProduto(produtoBD.getIdProduto() + "" + file.getOriginalFilename());
			Produto produtoAtualizado = produtoRepository.save(produtoBD);

			arquivoService.criarArquivo(produtoBD.getIdProduto() + "" + file.getOriginalFilename(), file);
			return converterEntityToDTO(produtoAtualizado);
		} else
			throw new NoSuchElementFoundException("O nome da imagem já existe");
	}

	public Boolean conferindoNome(MultipartFile file) {
		Boolean existe = produtoRepository.existsByNomeImagem(file.getOriginalFilename());

		if (existe) {
			return true;
		}
		return false;

	}
}