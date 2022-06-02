package com.residencia.commerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer idEndereco;

	@Column(name = "cep")
	@NotBlank(message = "O cep é obrigatório")
	@Length(min = 8, max = 8, message = "O cep deverá ter no minímo 3 {mim} caracteres e no máximo {max}")
	@Pattern(regexp = "\\d{5}-?\\d{3}", message = "O cep deve respeitar o formato 'XXXXX-XXX'.")
	private String cepEndereco;

	@Column(name = "rua")
	@NotBlank(message = "A rua é obrigatória")
	@Length(min = 3, max = 35, message = "A rua deverá ter no minímo 3 {mim} caracteres")
	private String ruaEndereco;

	@Column(name = "bairro")
	@NotBlank(message = "O bairro é obrigatório")
	private String bairroEndereco;

	@Column(name = "cidade")
	@NotBlank(message = "A cidade é obrigatória")
	private String cidadeEndereco;

	@Column(name = "numero")
	@NotNull(message = "O numero é obrigatório")
	private Integer numeroEndereco;

	@Column(name = "complemento")
	@NotBlank(message = "O complemento é obrigatório")
	private String complemetnoEndereco;

	@Column(name = "uf")
	@NotBlank(message = "A uf é obrigatória")
	@Length(min = 2, max = 2, message = "A UF deverá ter 2 caracteres")
	private String ufEndereco;

	@OneToMany(mappedBy = "endereco")
	@JsonIgnore
	private List<Cliente> clienteList;

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCepEndereco() {
		return cepEndereco;
	}

	public void setCepEndereco(String cepEndereco) {
		this.cepEndereco = cepEndereco;
	}

	public String getRuaEndereco() {
		return ruaEndereco;
	}

	public void setRuaEndereco(String ruaEndereco) {
		this.ruaEndereco = ruaEndereco;
	}

	public String getBairroEndereco() {
		return bairroEndereco;
	}

	public void setBairroEndereco(String bairroEndereco) {
		this.bairroEndereco = bairroEndereco;
	}

	public String getCidadeEndereco() {
		return cidadeEndereco;
	}

	public void setCidadeEndereco(String cidadeEndereco) {
		this.cidadeEndereco = cidadeEndereco;
	}

	public Integer getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(Integer numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getComplemetnoEndereco() {
		return complemetnoEndereco;
	}

	public void setComplemetnoEndereco(String complemetnoEndereco) {
		this.complemetnoEndereco = complemetnoEndereco;
	}

	public String getUfEndereco() {
		return ufEndereco;
	}

	public void setUfEndereco(String ufEndereco) {
		this.ufEndereco = ufEndereco;
	}

	public List<Cliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

}
