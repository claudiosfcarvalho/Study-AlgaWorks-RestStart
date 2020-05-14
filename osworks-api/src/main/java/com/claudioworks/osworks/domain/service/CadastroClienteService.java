package com.claudioworks.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudioworks.osworks.domain.exception.NegocioException;
import com.claudioworks.osworks.domain.model.Cliente;
import com.claudioworks.osworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExist = clienteRepository.findByEmail(cliente.getEmail());
		if(clienteExist != null && !clienteExist.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email");
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long id) {
		clienteRepository.deleteById(id);;
	}
}
