package br.com.luisaaugustoferreira.modelagemconceitual.domain;

import java.util.Date;

import br.com.luisaaugustoferreira.modelagemconceitual.domain.enums.EstadoPagamento;

public class PagamentoComBoleto extends Pagamento {
	private Date dataVencimento;
	private Date dataPagamento;
	

	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}


}
