package com.claudioworks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudioworks.osworks.domain.model.Cliente;
import com.claudioworks.osworks.domain.model.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> { 

	List<OrdemServico> findByCliente(Cliente cliente);
	List<OrdemServico> findByDescricaoContaining(String descricao);
}
