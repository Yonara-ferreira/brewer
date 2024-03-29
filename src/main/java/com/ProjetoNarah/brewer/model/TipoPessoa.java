package com.ProjetoNarah.brewer.model;

import com.ProjetoNarah.brewer.model.validation.group.CnpjGroup;
import com.ProjetoNarah.brewer.model.validation.group.CpfGroup;

public enum TipoPessoa {

	FISICA ("física", "CPF", "000.000.000.00", CpfGroup.class),
	JURIDICA("juridica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class);
	
	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;
	
	TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
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

	public Class<?> getGrupo() {
		return grupo;
	}
	
	public static String removerFormatacao(String cpfOuCnpj) {
		return cpfOuCnpj.replaceAll("\\.|-|/", "");
	}
	
	
}
