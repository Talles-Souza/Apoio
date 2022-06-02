package com.residencia.commerce.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Column(name = "email")
	@NotBlank(message = "O email é obrigatório")
	@Email(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
	private String emailCliente;

	@Column(name = "nome_completo")
	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 3, max = 35, message = "O nome deverá ter no máximo {max} caracteres")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "O nome não pode começar com letras minúsculas e só deve conter letras.")
	private String nomeCompletoCliente;

	@Column(name = "cpf")
	@NotBlank(message = "O cpf é obrigatório")
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve respeitar o formato 'XXX-XXX-XXX-XX'.")
	private String cpfCliente;

	@Column(name = "telefone")
	@NotBlank(message = "O telefone é obrigatório")
	@Pattern(regexp = "([0-9]{2,3})?([0-9]{2})([0-9]{4,5})([0-9]{4})", message = "O telefone deve respeitar o formato válido.")
	private String telefoneCliente;

	@Column(name = "data_pedido")
	@NotBlank(message = "A data do pedido é obrigatória")
	@Pattern(regexp = "/(\\d{2})\\/(\\d{2})\\/(\\d{4})/", message = "A data deve respeitar o formato XX/XX/XXXX")
	private Date dataNascimentoCliente;

	@ManyToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getNomeCompletoCliente() {
		return nomeCompletoCliente;
	}

	public void setNomeCompletoCliente(String nomeCompletoCliente) {
		this.nomeCompletoCliente = nomeCompletoCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public Date getDataNascimentoCliente() {
		return dataNascimentoCliente;
	}

	public void setDataNascimentoCliente(Date dataNascimentoCliente) {
		this.dataNascimentoCliente = dataNascimentoCliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
