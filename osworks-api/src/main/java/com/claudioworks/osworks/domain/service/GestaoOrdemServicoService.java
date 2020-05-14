package com.claudioworks.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudioworks.osworks.domain.exception.NegocioException;
import com.claudioworks.osworks.domain.model.Cliente;
import com.claudioworks.osworks.domain.model.Comentario;
import com.claudioworks.osworks.domain.model.OrdemServico;
import com.claudioworks.osworks.domain.model.StatusOrdemServico;
import com.claudioworks.osworks.domain.repository.ClienteRepository;
import com.claudioworks.osworks.domain.repository.ComentarioRepository;
import com.claudioworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
								.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long id, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(id)
								.orElseThrow(() -> new NegocioException("Ordem de serviço não encontrada"));
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		return comentarioRepository.save(comentario);
	}
}
