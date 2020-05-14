package com.claudioworks.osworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//anotação para retirar do json os campos null no retorno, assim se tiver algum nao preenchido nao aparece no retorno
@JsonInclude(Include.NON_NULL)
public class ProblemaHandler {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<CampoHandler> campos;
	
	
	public List<CampoHandler> getCampos() {
		return campos;
	}
	public void setCampos(List<CampoHandler> campos) {
		this.campos = campos;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public OffsetDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
