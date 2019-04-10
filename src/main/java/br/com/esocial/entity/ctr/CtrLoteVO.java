package br.com.esocial.entity.ctr;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TSOC_CTR_LOTE",schema="ESOCIAL")
public class CtrLoteVO {
	
	@Id
	private Integer	idLote;
	private Integer	idPeriodoDet;
	private Integer	idOrigem;
	private Integer	idEvento;
	private Integer	codIns;
	private String	flgStatus;
	private String	wsProtocoloEnvio;
	private Calendar	ctrDatEnvio;
	private String	wsProtocoloRetorno;
	private Calendar	ctrDatRetorno;
	private Integer	qtdEvento;
	private Calendar	dataEstimadaProc;
	private String	xmlLote; //CLOB
	private Calendar	datIng;
	private Calendar	datUltAtu;
	private String	nomUsuUltAtu;
	private String	nomProUltAtu;
	private Integer	idCtrProcesso;
	private String	wsXmlEnvio; //CLOB
	private String	wsXmlRetorno; //CLOB
	private String	wsCodRespostaEnv;
	private String	wsDscRespostaEnv;
	private String	wsDscErroRet;
	private String	wsDhRecepcaoEnv;
	private String	wsVerAppRecepEnv;
	
	private String wsCodRespostaRet;
	private String wsDscRespostaRet;
	private String wsVerAppProcRet;
	private String wsDscErroEnv;
	
	
	
	


}
