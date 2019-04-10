package br.com.esocial.estrutural.util;

public enum TipoAcao {
	I("Inclusão"), A("Alteração"), E("Exclusão");

	private String descricao;

	TipoAcao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
