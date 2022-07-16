package com.ProjetoNarah.brewer.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Usuario {

	private String codigo;
	
	@NotBlank(message = "Nome é obrigatorio")
	private String nome;
	
	@NotBlank(message = "E-mail é obrigatoria")
	private String email;
	
	@Size(min=5, max=15, message = "Senha nao corresponde com os requisitos" )
	@NotBlank(message = "Por gentileza,insira uma senha!")
	private String senha;
	
	private String confirmacaoDeSenha;
	
	
	private int dataDeNasc;
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getDataDeNasc() {
		return dataDeNasc;
	}
	public void setDataDeNasc(int dataDeNasc) {
		this.dataDeNasc = dataDeNasc;
	}
	public String getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}
	public void setConfirmacaoDeSenha(String confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}
	
	
	
}
