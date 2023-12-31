package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;

@Embeddable //significa que a classe � embutivel dentro de outra entidade
public class DadosPessoais {

	private String nome;
	private String cpf;

	public DadosPessoais(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public DadosPessoais() {
		
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
}
