package com.residencia.commerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.commerce.dto.ProdutoDTO;
import com.residencia.commerce.entity.Categoria;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@GetMapping
	@Operation(summary = "Listar todos os produtos", description = "Listagem de produtos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<List<ProdutoDTO>> findAllProduto() {
		List<ProdutoDTO> produtoList = produtoService.findAllProduto();
		return new ResponseEntity<>(produtoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retornar um produto", description = "Produto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer id) {
		ProdutoDTO produtoDTO = produtoService.findProdutoById(id);
		if (null == produtoDTO)
			throw new NoSuchElementFoundException("Não foi encontrado Produto com id: " + id + " pois não existe");
		else
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Inserir os dados de produto", description = "Produto adicionado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
		ProdutoDTO novoProduto = produtoService.saveProduto(produtoDTO);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}

	@PostMapping(value = "/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@Operation(summary = "Inserir os dados de produto e foto", description = "Produto adicionado com foto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> saveCategoriaComfoto(@RequestPart("produto") String produto,
			@RequestPart("file") MultipartFile file) throws Exception {
		ProdutoDTO novoProduto = produtoService.saveProdutoComFoto(produto, file);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualizar os dados de produto", description = "Produto atualizado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
		ProdutoDTO produtoAtualizado = produtoService.findProdutoById(produtoDTO.getIdProduto());
		if (null == produtoAtualizado)
			throw new NoSuchElementFoundException("Não foi possivel atualizar Produto com este id");
		else
			return new ResponseEntity<>(produtoService.updateProduto(produtoDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remover um produto", description = "Produto removido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<String> deleteProduto(@PathVariable Integer id) {
		ProdutoDTO ProdutoAtualizado = produtoService.findProdutoById(id);
		if (null == ProdutoAtualizado)
			throw new NoSuchElementFoundException(
					"Não foi possível deletar Produto com id: " + id + " pois não existe");
		else
			produtoService.deleteProdutoById(id);
		return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
	}
}
