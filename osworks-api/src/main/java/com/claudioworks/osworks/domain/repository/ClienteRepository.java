package com.claudioworks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudioworks.osworks.domain.model.Cliente;

//aqui define como um componente do spring
//o parm é a classe e o tipo do id da classe
//o spring data jpa ja faz a implementacao desta classe automaticamente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	//List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
	Cliente findByEmail(String email);
}
