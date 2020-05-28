package com.claudioworks.socialBooksApi.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
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

import com.claudioworks.socialBooksApi.domain.Comentario;
import com.claudioworks.socialBooksApi.domain.Livro;
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
		Livro livro = livrosService.buscar(id);
		// se der exception será tratado no handler
		
		//quando executar o buscar o cache do cliente sera preenchido com a validade de  20 segundos
		//assim que houver outra chamada antes de vencer esse tempo os dados apresentado serão dos dados 
		//apresentado em cache
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}

	@PostMapping
	public ResponseEntity<Void> adiciona(@RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualiza(@PathVariable("id") Long id, @Valid @RequestBody Livro livro) {
		Livro livroOrig = null;
		livroOrig = livrosService.buscar(id);
		if (livroOrig != null) {
			livroOrig.setAutor(livro.getAutor() != null ? livro.getAutor() : livroOrig.getAutor());
			livroOrig.setNome(livro.getNome() != null ? livro.getNome() : livroOrig.getNome());
			livroOrig.setPublicacao(livro.getPublicacao() != null ? livro.getPublicacao() : livroOrig.getPublicacao());
			livroOrig.setResumo(livro.getResumo() != null ? livro.getResumo() : livroOrig.getResumo());
			livro = livrosService.atualizar(livroOrig);
		}
		;

		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

		livrosService.deletar(id);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @Valid @RequestBody Comentario comentario) {
		livrosService.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentario(@PathVariable("id") Long livroId) {
		var comentarios = livrosService.listarComentario(livroId);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}
