package com.claudioworks.socialBooksApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudioworks.socialBooksApi.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

	public Livro findByNome(String nome);

}
