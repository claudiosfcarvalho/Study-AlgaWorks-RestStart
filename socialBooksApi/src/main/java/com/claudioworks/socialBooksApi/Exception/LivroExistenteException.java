package com.claudioworks.socialBooksApi.Exception;

public class LivroExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivroExistenteException(String msg) {
		super(msg);
	}
	
	public LivroExistenteException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
