package br.com.alura.loja.testes;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Informatica;
import br.com.alura.loja.modelo.Livro;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		produtoDao.buscarPorProdutosComCriterios("PlayStation", null, LocalDate.now());
	}

	private static void popularBancoDeDados() {
		Livro livro = new Livro("Rick Sanches", 180);
		Informatica informatica = new Informatica("Logtech", 343);
        
        Cliente cliente = new Cliente("Rodrigo", "123456");
        
        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);
       
        EntityManager em = JPAUtil.getEntityManager();
        
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        em.getTransaction().begin();
        
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);
        
        produtoDao.cadastrar(livro);
        produtoDao.cadastrar(informatica);

        clienteDao.cadastrar(cliente);
        clienteDao.cadastrar(cliente);
      
        em.getTransaction().commit();
        em.close();
	}
}
