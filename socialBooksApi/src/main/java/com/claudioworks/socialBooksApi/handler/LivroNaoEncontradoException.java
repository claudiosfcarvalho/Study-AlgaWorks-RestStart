package com.claudioworks.socialBooksApi.handler;

public class LivroNaoEncontradoException extends RuntimeException{

	public LivroNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public LivroNaoEncontradoException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
