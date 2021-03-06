package com.claudioworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.claudioworks.osworks.domain.model.Cliente;
import com.claudioworks.osworks.domain.repository.ClienteRepository;
import com.claudioworks.osworks.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	//com essa anotação a instacia é colocada automaticamente no manager
	//@PersistenceContext
	//private EntityManager manager;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
					//a forma abaixo seria para acesso direto sem o spring data jpa controlando
					/*		return manager.
					createQuery("from Cliente", Cliente.class). //linguagem jpql trazendo consulta do cliente
					getResultList();							//retorna lista da consulta acima
					*/
	}
	
	@GetMapping("/nome={nome}")
	public List<Cliente> listarPorNome(@PathVariable String nome) {
		return clienteRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/id={id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//o @valid ativa as validacoes bean validation (da especificacao jakarta implementado pelo hibernate)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente);
		return cadastroClienteService.salvar(cliente);
	}
	
	@PutMapping("/id={id}")
	public ResponseEntity<Cliente> alterar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		//cliente = clienteRepository.save(cliente);
		cliente = cadastroClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/id={id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		//clienteRepository.deleteById(id);
		cadastroClienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
