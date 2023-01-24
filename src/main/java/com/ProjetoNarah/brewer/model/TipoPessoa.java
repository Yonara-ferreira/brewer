package com.ProjetoNarah.brewer.model;

public enum TipoPessoa {

	FISICA ("física", "CPF", "000.000.000.00"),
	JURIDICA("juridica", "CNPJ", "00.000.000/0000-00");
	
	private String descricao;
	private String documento;
	private String mascara;
	
	TipoPessoa(String descricao, String documento, String mascara) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}
}
