package com.claudioworks.socialBooksApi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@NotEmpty(message = "Texto do comentário precisa ser preenchido")
	@Size(max=1500, message = "Tamanho máximo nome é de 1500 caracteres")
	@JsonProperty("comentario")
	private String texto;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@NotEmpty(message = "Nome do usuário precisa ser preenchido")
	@Size(max=255, message = "Tamanho máximo nome é de 255 caracteres")
	private String usuario;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "livro_id")
	@JsonIgnore
	private Livro livro;
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

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
