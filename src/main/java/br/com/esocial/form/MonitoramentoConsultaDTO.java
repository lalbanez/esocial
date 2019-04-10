package br.com.esocial.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MonitoramentoConsultaDTO {
	
	private String periodo;
	private String origem;
	private String evento;
	private String codEvento;
	private String statusEvento;
	private String idEvento;
	private String protocoloEnvio;
	private String numRecibo; 
	private String dataGeracao;
	private String dataEnvio;
	private String dataRetorno;
	private String idEmpregador;
	private String codRubrica;
}

