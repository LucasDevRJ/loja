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
		System.out.println("Busca produto por ID");
		Produto produto = produtoDao.buscarPorId(1l);
		System.out.println(produto.getPreco());
		System.out.println();
		
		System.out.println("Busca todos os produtos");
		List<Produto> produtos = produtoDao.buscarTodos();
		produtos.forEach(p -> System.out.println(p));
		System.out.println();
		
		System.out.println("Busca todos os produtos por nome");
		List<Produto> produtos2 = produtoDao.buscarPorNome("Samsung Galaxy");
		produtos2.forEach(p -> System.out.println(p));
		System.out.println();
		
		System.out.println("Buscar produtos por categoria");
		List<Produto> produtos3 = produtoDao.buscarPorCategoria("CELULARES");
		produtos3.forEach(p -> System.out.println(p));
		
		System.out.println();
		System.out.println("Buscar produto pelo nome");
		BigDecimal precoProduto = produtoDao.buscarPrecoProduto("Motorola");
		System.out.println(precoProduto);
		
		System.out.println();
		System.out.println("Buscar produtos com o preço de até");
		List<Produto> produtos4 = produtoDao.buscarProdutosComPrecoAte(new BigDecimal("1000"));
		produtos4.forEach(p -> System.out.println(p));
		
		System.out.println();
		System.out.println("Buscar produtos com parâmetros");
		List<Produto> produtos5 = produtoDao.buscarProdutoPorParametros(null,  new BigDecimal("1200"), null);
		produtos5.forEach(p -> System.out.println(p));
		
		System.out.println();
		System.out.println("Buscar produtos com parâmetros criteria api");
		List<Produto> produtos6 = produtoDao.buscarProdutoPorParametros("PlayStation",  null, null);
		produtos6.forEach(p -> System.out.println(p));
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria consoles = new Categoria("CONSOLES");
		
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Produto celular2 = new Produto("Samsung Galaxy", "Muito legal 2", new BigDecimal("900"), celulares );
        Produto celular3 = new Produto("Motorola", "Muito legal 3", new BigDecimal("930"), celulares );
        Produto celular4 = new Produto("Samsung Galaxy", "Samsung Galaxy J8", new BigDecimal("1200"), celulares );
        
        Produto console = new Produto("Xbox", "Xbox One", new BigDecimal("1200"), consoles);
        Produto console2 = new Produto("PlayStation", "PlayStation 4", new BigDecimal("1600"), consoles);
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(consoles);
        
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(celular2);
        produtoDao.cadastrar(celular3);
        produtoDao.cadastrar(celular4);
        produtoDao.cadastrar(console);
        produtoDao.cadastrar(console2);

        em.getTransaction().commit();
        em.close();
	}
}
