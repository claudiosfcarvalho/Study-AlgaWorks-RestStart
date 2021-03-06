package com.claudioworks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudioworks.osworks.domain.model.Comentario;
import com.claudioworks.osworks.domain.model.OrdemServico;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findByOrdemServico(OrdemServico ordemServico);
}
