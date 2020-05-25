package com.claudioworks.socialBooksApi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "comentario_id")
	private Long id;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	private String texto;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	private String usuario;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
