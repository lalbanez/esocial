package br.com.esocial.estrutural.util;

public enum StatusEventEnum {
	AX("Aguardando geração de XML"), AA("Aguardando Assinatura"), AL("Aguardando Geração do Lote"),
	EV("Erro de Validação"), EX("Erro na geração de XML"), EA("Erro na Assinatura"), EL("Erro na Geração do Lote"),
	AE("Aguardando Envio"), AR("Enviado"), EE("Erro no Envio"), RE("Retornado com Erro"), FN("Finalizado");

	private String descricao;

	StatusEventEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
