package com.claudioworks.socialBooksApi.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudioworks.socialBooksApi.domain.Livro;
import com.claudioworks.socialBooksApi.handler.LivroNaoEncontradoException;
import com.claudioworks.socialBooksApi.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	@Autowired
	private LivrosService livrosService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Livro livro = null;
		try {
			livro = livrosService.buscar(id);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}

	@PostMapping("/add")
	public ResponseEntity<Void> adiciona(@RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualiza(@PathVariable("id") Long id, @RequestBody Livro livro) {
		Livro livroOrig = null;
		try {
			livroOrig = livrosService.buscar(id);
			if (livroOrig != null) {
				livroOrig.setAutor(livro.getAutor() != null ? livro.getAutor() : livroOrig.getAutor());
				livroOrig.setNome(livro.getNome() != null ? livro.getNome() : livroOrig.getNome());
				livroOrig.setPublicacao(
						livro.getPublicacao() != null ? livro.getPublicacao() : livroOrig.getPublicacao());
				livroOrig.setResumo(livro.getResumo() != null ? livro.getResumo() : livroOrig.getResumo());
				livro = livrosService.atualizar(livroOrig);
			};
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		try {
			livrosService.deletar(id);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
