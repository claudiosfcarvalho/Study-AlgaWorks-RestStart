package com.claudioworks.socialBooksApi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.claudioworks.socialBooksApi.Exception.AutorExistenteException;
import com.claudioworks.socialBooksApi.Exception.AutorNaoEncontradoException;
import com.claudioworks.socialBooksApi.Exception.LivroNaoEncontradoException;
import com.claudioworks.socialBooksApi.domain.DetalhesErro;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerLivroNaoEncontradoException
									(LivroNaoEncontradoException e, HttpServletRequest request) {
		
		var erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage() == null ? "O livro não pôde ser encontrado" : e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerAutorNaoEncontradoException
									(AutorNaoEncontradoException e, HttpServletRequest request) {
		
		var erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage() == null ? "O autor não pôde ser encontrado" : e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handlerAutorExistenteException
									(AutorExistenteException e, HttpServletRequest request) {
		
		var erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo(e.getMessage() == null ? "O autor já existe" : e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
}
