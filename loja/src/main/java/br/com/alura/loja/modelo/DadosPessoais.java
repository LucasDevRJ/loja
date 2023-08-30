package br.com.alura.loja.modelo;

public class DadosPessoais {

	private String nome;
	private String cpf;
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
}
