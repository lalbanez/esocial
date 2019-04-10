
package br.com.esocial.leandro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name="tsoc_par_processo", schema="ESOCIAL")
public class LoteVO {

	private String ID_CTR_PROCESSO;

	private String ID_PROCESSO;

	private String ID_PERIODO;

	private String ID_COD_INS;

	private String FAIXA_INI;

	private String FAIXA_FIM;

	private String DAT_INICIO;

	private String DAT_FIM;

	private String FLG_STATUS;

	private String QTD_REGISTROS;

	private String DAT_ING;

	private String DAT_ULT_ATUALIZACAO;

	private String NOM_USU_ULT_ATU;

	private String PID;

	private String SID;

	private String ID_EVENTO;

	private String PAR_MINUTO;

	private String PAR_HORA;

	private String PAR_DIA;

	private String PAR_MES;

	private String XML_WS_ENVIO;

	private String XML_LOTE;

	private String XML_WS_RETORNO;

	private String ID_LOTE;

	private String WS_PROTOCOLO_ENVIO;
	
	private String RETURN_ERRO;
	
	private String COUNT_CHECK_OPEN_ROW;
	
	private String CRONTAB_FORMAT;
	
}
