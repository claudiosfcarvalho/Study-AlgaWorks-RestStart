package com.claudioworks.socialBooksApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudioworks.socialBooksApi.domain.Comentario;


public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
