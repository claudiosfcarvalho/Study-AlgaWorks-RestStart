package com.claudioworks.socialbooksapi.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.claudioworks.socialbooksapi.client.domain.Livro;

public class LivrosClient {

	private String URI_BASE;
	private String URN_BASE = "/livros";
	private RequestEntity<?> request = null;
	private RestTemplate restTemplate;
	private String credencial;
	public LivrosClient( String url, String usuario, String senha) {
		restTemplate = new RestTemplate();
		URI_BASE = url.concat(URN_BASE);
		String credencialAux = usuario + ":" + senha;
		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}
	public List<Livro> listar() {
		request= RequestEntity
				.get(URI.create(URI_BASE))
				.header("Authorization", credencial)
				.build();

		ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro) {
		request= RequestEntity
				.post(URI.create(URI_BASE))
				.header("Authorization", credencial)
				.body(livro);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
	
	public Livro buscarPorURI(String localizacao) {
		request= RequestEntity
				.get(URI.create(localizacao))
				.header("Authorization", credencial)
				.build();

		ResponseEntity<Livro> response = restTemplate.exchange(request, Livro.class);
		
		return response.getBody();
	}
}
