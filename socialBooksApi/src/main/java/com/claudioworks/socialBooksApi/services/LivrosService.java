package com.claudioworks.socialBooksApi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudioworks.socialBooksApi.Exception.AutorExistenteException;
import com.claudioworks.socialBooksApi.Exception.LivroNaoEncontradoException;
import com.claudioworks.socialBooksApi.domain.Comentario;
import com.claudioworks.socialBooksApi.domain.Livro;
import com.claudioworks.socialBooksApi.repository.ComentarioRepository;
import com.claudioworks.socialBooksApi.repository.LivrosRepository;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public List<Livro> listar() {
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long id) {
		Optional<Livro> livro = livrosRepository.findById(id);
		if (!livro.isPresent()) {
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
		}
		return livro.get();
	}
	
	public Livro salvar(Livro livro) {
		var livroExistente = livrosRepository.findByNome(livro.getNome());
		if (livroExistente != null) {
			throw new AutorExistenteException("Livro já está cadastrado");
		}
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	public Livro atualizar(Livro livro) {
		verificarExistencia(livro);
		return livrosRepository.save(livro);
	}
	
	private void verificarExistencia (Livro livro ) {
		buscar(livro.getId());
	}
	
	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);	
		} catch(EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não encontrado para deleção");
		}
	}
	
	public Comentario salvarComentario(Long LivroId, Comentario comentario) {
		var livro = buscar(LivroId);
		comentario.setId(null);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentarioRepository.save(comentario);
	}
	
	public List<Comentario> listarComentario(Long LivroId) {
		var livro = buscar(LivroId);
		return livro.getComentarios();
	}
}
