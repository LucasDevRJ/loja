package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente );
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL VENDIDO: " + totalVendido);
		
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria consoles = new Categoria("CONSOLES");
		
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Produto celular2 = new Produto("Samsung Galaxy", "Muito legal 2", new BigDecimal("900"), celulares );
        Produto celular3 = new Produto("Motorola", "Muito legal 3", new BigDecimal("930"), celulares );
        Produto celular4 = new Produto("Samsung Galaxy", "Samsung Galaxy J8", new BigDecimal("1200"), celulares );
        
        Produto console = new Produto("Xbox", "Xbox One", new BigDecimal("1200"), consoles);
        Produto console2 = new Produto("PlayStation", "PlayStation 4", new BigDecimal("1600"), consoles);
        
        Cliente cliente = new Cliente("Rodrigo", "111.222.333-11");
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(consoles);
        
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(celular2);
        produtoDao.cadastrar(celular3);
        produtoDao.cadastrar(celular4);
        produtoDao.cadastrar(console);
        produtoDao.cadastrar(console2);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
	}
}
