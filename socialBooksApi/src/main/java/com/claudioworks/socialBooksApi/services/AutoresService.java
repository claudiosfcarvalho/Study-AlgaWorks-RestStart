package com.claudioworks.socialBooksApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudioworks.socialBooksApi.Exception.AutorExistenteException;
import com.claudioworks.socialBooksApi.Exception.AutorNaoEncontradoException;
import com.claudioworks.socialBooksApi.domain.Autor;
import com.claudioworks.socialBooksApi.repository.AutoresRepository;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar() {
		return autoresRepository.findAll();
	}
	
	public Autor buscar(Long id) {
		Optional<Autor> autor = autoresRepository.findById(id);
		if (!autor.isPresent()) {
			throw new AutorNaoEncontradoException("O Autor não pode ser encontrado");
		}
		return autor.get();
	}
	
	public Autor salvar(Autor autor) {
		var autorExistente = autoresRepository.findByNome(autor.getNome());
		if (autorExistente != null) {
			throw new AutorExistenteException("Autor já está cadastrado");
		}
		autor.setId(null);
		return autoresRepository.save(autor);
	}

	public Autor atualizar(Autor autor) {
		verificarExistencia(autor);
		return autoresRepository.save(autor);
	}

	private void verificarExistencia(Autor autor) {
		if (autor.getId() != null)
			buscar(autor.getId());
	}
	
	public void deletar(Long id) {
		buscar(id);
		autoresRepository.deleteById(id);
	}
}
