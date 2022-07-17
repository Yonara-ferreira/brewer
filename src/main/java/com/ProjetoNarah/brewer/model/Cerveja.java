package com.ProjetoNarah.brewer.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Cerveja {

	@NotBlank(message = "SKU é obrigatorio")
	private String sku;
	
	@NotBlank(message = "Nome é obrigatoria")
	private String nome;
	
	@NotBlank(message = "A descrição é obrigatoria")
	@Size(min=1, max=50, message = "o tamanho da descrição deve esta entre 1 e 50")
	private String descricao;
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	

}
