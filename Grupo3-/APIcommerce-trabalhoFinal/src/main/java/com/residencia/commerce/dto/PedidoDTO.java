package com.residencia.commerce.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoDTO {

	private Integer idPedido;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPedido;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataEntregaPedido;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataEnvioPedido;
	
	private Boolean statusPedido;
	
	private ClienteDTO clienteDTO;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntregaPedido() {
		return dataEntregaPedido;
	}

	public void setDataEntregaPedido(Date dataEntregaPedido) {
		this.dataEntregaPedido = dataEntregaPedido;
	}

	public Date getDataEnvioPedido() {
		return dataEnvioPedido;
	}

	public void setDataEnvioPedido(Date dataEnvioPedido) {
		this.dataEnvioPedido = dataEnvioPedido;
	}

	public Boolean getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(Boolean statusPedido) {
		this.statusPedido = statusPedido;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

}
