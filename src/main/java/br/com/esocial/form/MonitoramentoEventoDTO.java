package br.com.esocial.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MonitoramentoEventoDTO {
	
	private String idOrigem = "";
	private String idPeriodo = "";
	private String idEvento = "";
	
	private String nomTabela = "";
	private String codEvento = "";
	private String descEvento = "";
	private String tipEvento = "";
	
	private String descStatusEvento = "";
	private String descPeriodo = "";
	private String descOrigem = "";
	private String totalStatusEvento = "";
	
	
	private String preEnvioEventosGerados  =  "";
	private String preEnvioEventosValidados  =  "";
	private String preEnvioEventosComErro  = "";
	private String envioAguardandoEnvio  =  "";
	private String envioEventosEnviados = "";
	private String envioEventosComErro = "";
	private String envioEventosFinalizados = "";
	
	private String total = "";
	private String erroValidacao = "";
	private String aguardandoGeracaoXml = "";
	private String erroGeracaoXml = "";
	private String aguardandoAssinatura = "";
	private String erroAssinatura = "";
	private String aguardandoGeracaoLote = "";
	private String erroGeracaoLote = "";
	private String aguardandoEnvio = "";
	private String erroNoEnvio = "";
	private String enviado = "";
	private String retornadoComErro = "";
	private String finalizado= "";
	
}
