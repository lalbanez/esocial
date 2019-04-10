package br.com.esocial.empregador;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TSOC_1000_EMPREGADOR", schema="ESOCIAL")
public class TriatEventoVO2 {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceSeqId10002")
	@SequenceGenerator(name = "sequenceSeqId10002", sequenceName = "ESOC_SEQ_ID_1000" )
	private Integer idPk;
	
	private Integer codIns;	
	private String id;	
	private Integer tpamb;	
	private Integer procemi;	
	private String verproc;	
	private Integer tpinsc;	
	private String nrinsc;	
	private String ideperiodoInivalid;	
	private String ideperiodoFimvalid;	
	private String infocadastroNmrazao;	
	private String infocadastroClasstrib;	
	private String infocadastroNatjurid;	
	private Integer infocadastroIndcoop;	
	private Integer infocadastroIndconstr;	
	private Integer infocadastroInddesfolha;	
	private Integer infocadastroIndoptregeletron;	
	private String infocadastroIndented;	
	private String infocadastroIndett;	
	private String infocadastroNrregett;	
	private String dadosisencaoIdeminlei;	
	private String dadosisencaoNrcertif;	
	private Calendar dadosisencaoDtemiscertif;	
	private Calendar dadosisencaoDtvenccertif;	
	private String dadosisencaoNrprotrenov;	
	private Calendar dadosisencaoDtprotrenov;	
	private Calendar dadosisencaoDtdou;	
	private Integer dadosisencaoPagdou;	
	private String contatoNmctt;	
	private String contatoCpfctt;	
	private String contatoFonefixo;	
	private String contatoFonecel;	
	private String contatoEmail;	
	private String infoopIndugrpps;	
	private Integer infoopEsferaop;	
	private Integer infoopPoderop;	
	private Integer infoopVrtetorem;
	private String infoopIdeefr;	
	private String infoopCnpjefr;	
	private String infoefrIdeefr;	
	private String infoefrCnpjefr;	
	private String infoenteNmente;	
	private String infoenteUf;	
	private Integer infoenteCodmunic;	
	private String infoenteIndrpps;	
	private Integer infoenteSubteto;	
	private Integer infoenteVrsubteto;	
	private Integer infoorginterIndacordoinsenmul;	
	private String softwarehouseCnpjsofthouse;	
	private String softwarehouseNmrazao;	
	private String softwarehouseNmcont;	
	private String softwarehouseTelefone;	
	private String softwarehouseEmail;	
	private Integer situacaopjIndsitpj;	
	private Integer situacaopfIndsitpf;	
	private String novavalidadeInivalid;	
	private String novavalidadeFimvalid;	
	private Calendar datIng;	
	private Calendar datUltAtu;	
	private String nomUsuUltAtu;	
	private String nomProUltAtu;
	
	private Integer idOrigem;// entidade
	
	private Integer idPeriodoDet;//entidade
	
	private String ctrNumCpf;	
	
	private Integer idLote;//entidade
	
	private String ctrFlgStatus;//entidade
	
	private String xmlEnvio;	//CLOB
	
	private Integer idEvento;//entidade
	
	private String ctrFlgAcao;//entidade
	
	private Integer wsCodResposta;	
	private String wsDscResposta;	
	private String wsDhProc;	
	private String wsVerAppProc;	
	private Character flgVigencia;	
	

	
	
	
}