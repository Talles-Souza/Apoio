package com.residencia.commerce.dto;

public class ItemPedidoDTO {

	private Integer idItemPedido;
	private Integer quantidadeItemProduto;
	private Double precoVendaItemPedido;
	private Double percentualDescontoItemPedido;
	private Double valorBrutoItemPedido;
	private Double valorLiquidoItemPedido;
	private PedidoDTO pedidoDTO;
	private ProdutoDTO produtoDTO;

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidadeItemProduto() {
		return quantidadeItemProduto;
	}

	public void setQuantidadeItemProduto(Integer quantidadeItemProduto) {
		this.quantidadeItemProduto = quantidadeItemProduto;
	}

	public Double getPrecoVendaItemPedido() {
		return precoVendaItemPedido;
	}

	public void setPrecoVendaItemPedido(Double precoVendaItemPedido) {
		this.precoVendaItemPedido = precoVendaItemPedido;
	}

	public Double getPercentualDescontoItemPedido() {
		return percentualDescontoItemPedido;
	}

	public void setPercentualDescontoItemPedido(Double percentualDescontoItemPedido) {
		this.percentualDescontoItemPedido = percentualDescontoItemPedido;
	}

	public Double getValorBrutoItemPedido() {
		return valorBrutoItemPedido;
	}

	public void setValorBrutoItemPedido(Double valorBrutoItemPedido) {
		this.valorBrutoItemPedido = valorBrutoItemPedido;
	}

	public Double getValorLiquidoItemPedido() {
		return valorLiquidoItemPedido;
	}

	public void setValorLiquidoItemPedido(Double valorLiquidoItemPedido) {
		this.valorLiquidoItemPedido = valorLiquidoItemPedido;
	}

	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public ProdutoDTO getProdutoDTO() {
		return produtoDTO;
	}

	public void setProdutoDTO(ProdutoDTO produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

}
