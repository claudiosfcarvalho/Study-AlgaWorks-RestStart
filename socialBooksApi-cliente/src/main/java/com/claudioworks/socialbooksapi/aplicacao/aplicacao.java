package com.claudioworks.socialbooksapi.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;

import com.claudioworks.socialbooksapi.client.LivrosClient;
import com.claudioworks.socialbooksapi.client.domain.Livro;

public class aplicacao {

	private final static String URI = "http://localhost:8080";
	protected final static String USER ="algaworks";
	protected final static String PWD = "s3nh4";
	public static void main(String[] args) throws ParseException {
		try {
			LivrosClient client = new LivrosClient(URI,USER,PWD);
			List<Livro> listaLivros = client.listar();
			Integer qtdLivros = 0;
			for(Livro livro: listaLivros) {
				qtdLivros++;
				System.out.println("Livro "+ qtdLivros + ": " + livro.getNome());
			}
			if (qtdLivros == 0)
				System.out.println("Nenhum livro encontrado");
			else
				System.out.println(qtdLivros + " livro(s) encontrado(s)");
			
			//criando livro
			Livro livro = new Livro();
			livro.setNome("Testando client evoluido 2");
			SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
			livro.setPublicacao(publicacao.parse("01/12/2007"));
			livro.setResumo("Testando cliente uhu");
			String localizacao = client.salvar(livro);
			
			System.out.println("Retornado do save livro a localizacao: " + localizacao);
			
			Livro newLivro = client.buscarPorURI(localizacao);
			System.out.println("livro salvo->" + newLivro.getNome());
			
			client.buscarPorURI("http://localhost:8080/livros/123");
		} catch (HttpClientErrorException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.toString() + " # Responsebody: " + e.getResponseBodyAsString() + " # statustext " +e.getStatusText());
		}
	}
	
	

}
