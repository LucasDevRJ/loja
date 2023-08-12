package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(celulares);
		celulares.setNome("XPTO");
		
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares);
		celulares.setNome("1234");
		em.flush();
		em.clear();
		em.remove(celulares);	
		em.flush();
	}
}
