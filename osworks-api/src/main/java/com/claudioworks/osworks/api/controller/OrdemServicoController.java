package com.claudioworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.claudioworks.osworks.api.model.OrdemServicoInput;
import com.claudioworks.osworks.api.model.OrdemServicoModel;
import com.claudioworks.osworks.domain.exception.NegocioException;
import com.claudioworks.osworks.domain.model.Cliente;
import com.claudioworks.osworks.domain.model.OrdemServico;
import com.claudioworks.osworks.domain.repository.ClienteRepository;
import com.claudioworks.osworks.domain.repository.OrdemServicoRepository;
import com.claudioworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		return toModel(gestaoOrdemServicoService.criar(ordemServico));
	}
	
	@GetMapping
	//public List<OrdemServico> listar(){
	public List<OrdemServicoModel> listar(){
		//return ordemServicoRepository.findAll();
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/id={id}")
	public ResponseEntity<OrdemServicoModel> listarPorOSId(@Valid @PathVariable Long id){
		
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);
		
		if (ordemServico.isPresent()) {
			//mapeando objetos para outro tipo //DTO usando modelmapper 
			OrdemServicoModel representationModel = toModel(ordemServico.get());
					
			//return ResponseEntity.ok(ordemServico.get());
			return ResponseEntity.ok(representationModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/desc={descricao}")
	public List<OrdemServicoModel> listarPorDescricao(@Valid @PathVariable String descricao){
		return toCollectionModel(ordemServicoRepository.findByDescricaoContaining(descricao));
	}
	
	@GetMapping("/cliente={clienteId}")
	public List<OrdemServicoModel> listarPorDescricao(@Valid @PathVariable Long clienteId){
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		return toCollectionModel(ordemServicoRepository.findByCliente(cliente));
	}
	
	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}
	
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico) {
		return ordensServico.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
}
