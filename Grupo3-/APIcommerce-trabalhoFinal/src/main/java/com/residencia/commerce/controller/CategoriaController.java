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

import com.residencia.commerce.entity.Categoria;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	 @Operation(summary="Listar todos as categorias", description  = "Listagem de categorias")
		@ApiResponses(value = { 
				  @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
				  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
				  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
				  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<List<Categoria>> findAllCategoria() {
        List<Categoria> categoriaList = categoriaService.findAllCategoria();
        return new ResponseEntity<>(categoriaList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary="Retornar uma categoria", description  = "Categoria")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findCategoriaById(id);
        if (null == categoria)
            throw new NoSuchElementFoundException("Não foi encontrado categoria com id: " + id + " pois não existe.");
        else
            return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary="Inserir os dados de categoria", description  = "Categoria adicionada")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<Categoria> saveCategoria(@RequestBody @Valid Categoria categoria) {
        return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary="Atualizar os dados de categoria", description  = "Categoria atualizada")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<Categoria> updateCategoria(@RequestBody @Valid Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.findCategoriaById(categoria.getIdCategoria());
        if (null == categoriaAtualizada)
            throw new NoSuchElementFoundException("Não foi possivel atualizar categoria com este id");
        else
            return new ResponseEntity<>(categoriaService.updateCategoria(categoria), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Remover uma categoria", description  = "Categoria removida")
   	@ApiResponses(value = { 
   			  @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
   			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
   			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
   			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<String> deleteCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findCategoriaById(id);
        if (null == categoria)
            throw new NoSuchElementFoundException("Não foi possivel deletar categoria com id: " + id + " pois não existe.");
        else
            categoriaService.deleteCategoriaById(id);
        return new ResponseEntity<>("Categoria deletada com sucesso!", HttpStatus.OK);
    }
	
}
