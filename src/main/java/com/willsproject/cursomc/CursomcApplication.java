package com.willsproject.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.willsproject.cursomc.domain.Categoria;
import com.willsproject.cursomc.domain.Cidade;
import com.willsproject.cursomc.domain.Cliente;
import com.willsproject.cursomc.domain.Endereco;
import com.willsproject.cursomc.domain.Estado;
import com.willsproject.cursomc.domain.ItemPedido;
import com.willsproject.cursomc.domain.Pagamento;
import com.willsproject.cursomc.domain.PagamentoComBoleto;
import com.willsproject.cursomc.domain.PagamentoComCartao;
import com.willsproject.cursomc.domain.Pedido;
import com.willsproject.cursomc.domain.Produto;
import com.willsproject.cursomc.domain.enums.EstadoPagamento;
import com.willsproject.cursomc.domain.enums.TipoCliente;
import com.willsproject.cursomc.repositories.CategoriaRepository;
import com.willsproject.cursomc.repositories.CidadeRepository;
import com.willsproject.cursomc.repositories.ClienteRepository;
import com.willsproject.cursomc.repositories.EnderecoRepository;
import com.willsproject.cursomc.repositories.EstadoRepository;
import com.willsproject.cursomc.repositories.ItemPedidoRepository;
import com.willsproject.cursomc.repositories.PagamentoRepository;
import com.willsproject.cursomc.repositories.PedidoRepository;
import com.willsproject.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{//CommandLineRunner permite implementar o metodo auxiliar para executar uma acao quando a aplicacao comecar

	@Autowired
	private CategoriaRepository categoriaRepository; //reponsável de acessar os dados e salvar eles, manipular
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mosue", 200.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays .asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@naosei.com", "123456789", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("11111111", "22222222"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "12", "Apto 30", "Jardim", "38220823", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Matos", "105", "Sala 49", "Centro", "22213121", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/01/2021 10:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/01/2021 15:00"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 12:30"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip3,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
