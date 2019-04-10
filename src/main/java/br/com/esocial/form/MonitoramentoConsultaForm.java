package br.com.esocial.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoramentoConsultaForm {
	
	private String origem = "";
	private String periodo = "";
	private String tipoEvento = "";
	private String evento = "";
	private String idEvento = "";
	private String numLote = "";
	private String dataEnvio = "";
	private String protocoloEnvio = "";
	private String dataRetorno = "";
	private String numRecibo = "";
	private String dataGeracao = "";
	private String numCpf = "";
	private String descStatus = "";
	private String agrupacaoStatus = "";
	
}
