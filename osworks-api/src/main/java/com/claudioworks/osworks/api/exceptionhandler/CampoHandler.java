package com.claudioworks.osworks.api.exceptionhandler;

public class CampoHandler {
	private String nome;
	private String mensagem;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public CampoHandler() {
		super();
	}
	
	public CampoHandler(String nome, String mensagem) {
		super();
		this.nome = nome;
		this.mensagem = mensagem;
	}

}
