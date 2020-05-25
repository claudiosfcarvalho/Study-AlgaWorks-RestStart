package com.claudioworks.socialBooksApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudioworks.socialBooksApi.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
