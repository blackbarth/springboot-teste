package br.com.luisaaugustoferreira.modelagemconceitual.domain;

import br.com.luisaaugustoferreira.modelagemconceitual.domain.enums.EstadoPagamento;

public class PagamentoComCartao extends Pagamento {
	private Integer numeroDeParcelas;
	


	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}



	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}



	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
