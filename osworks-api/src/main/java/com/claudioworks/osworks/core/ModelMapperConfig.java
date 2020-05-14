package com.claudioworks.osworks.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Aqui nesta classe gera uma instancia para o spring gerenciar
 * pois como o model mapper é uma biblioteca de terceiros
 * não é possível anotar o servico para ser gerenciado diretamente
 * entao gera esta classe para que a instancia seja obtida
 */
@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
