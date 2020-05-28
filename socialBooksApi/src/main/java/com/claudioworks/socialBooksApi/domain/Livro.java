package com.claudioworks.socialBooksApi.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})	
public class Livro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@NotEmpty(message = "Nome do livro precisa ser preenchido")
	@Size(max=255, message = "Tamanho máximo nome é de 255 caracteres")
	private String nome;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data do livro precisa ser preenchido")
	private Date publicacao;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	private String resumo;
	
	@JsonInclude(Include.NON_EMPTY) //só retorna valor se for not null
	@OneToMany(mappedBy = "livro") 
	private List<Comentario> comentarios;
	
	@JsonInclude(Include.NON_NULL) //só retorna valor se for not null
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;

	public Livro() {
		
	}
	
	public Livro(String livro) {
		this.nome = livro;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
