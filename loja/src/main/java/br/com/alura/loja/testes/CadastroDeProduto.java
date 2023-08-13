package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorId(1l);
		System.out.println(produto.getPreco());
		
		List<Produto> produtos = produtoDao.buscarTodos();
		produtos.forEach(p -> System.out.println(p));
		
		List<Produto> produtos2 = produtoDao.buscarPorNome("Samsung Galaxy");
		produtos2.forEach(p -> System.out.println(p));
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Produto celular2 = new Produto("Samsung Galaxy", "Muito legal 2", new BigDecimal("900"), celulares );
        Produto celular3 = new Produto("Motorola", "Muito legal 3", new BigDecimal("930"), celulares );
        Produto celular4 = new Produto("Samsung Galaxy", "Samsung Galaxy J8", new BigDecimal("1200"), celulares );
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(celular2);
        produtoDao.cadastrar(celular3);
        produtoDao.cadastrar(celular4);

        em.getTransaction().commit();
        em.close();
	}
}
