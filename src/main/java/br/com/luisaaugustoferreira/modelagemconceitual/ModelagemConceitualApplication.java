package br.com.luisaaugustoferreira.modelagemconceitual;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luisaaugustoferreira.modelagemconceitual.domain.Categoria;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.Cidade;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.Cliente;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.Endereco;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.Estado;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.ItemPedido;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.Pagamento;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.PagamentoComBoleto;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.PagamentoComCartao;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.Pedido;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.Produto;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.enums.EstadoPagamento;
import br.com.luisaaugustoferreira.modelagemconceitual.domain.enums.TipoCliente;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.CategoriaRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.CidadeRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.ClienteRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.EnderecoRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.EstadoRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.ItemPedidoRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.PagamentoRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.PedidoRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

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
		SpringApplication.run(ModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("985858948", "985784587"));
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardim", "13185012", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Flores 2", "301", "Apt 304", "Jardim", "13185013", cli1, c2);

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/07/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 09:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		//pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	}

}
