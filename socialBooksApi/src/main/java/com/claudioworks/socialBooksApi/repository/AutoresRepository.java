package com.claudioworks.socialBooksApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudioworks.socialBooksApi.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

	public Autor findByNome(String nome);
}
