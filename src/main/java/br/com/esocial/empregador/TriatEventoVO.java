package br.com.esocial.empregador;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

import br.com.esocial.entity.ctr.CtrLoteVO;
import br.com.esocial.entity.ctr.CtrPeriodoDetVO;
import br.com.esocial.entity.par.ParCodigoVO;
import br.com.esocial.entity.par.ParEventoVO;
import br.com.esocial.entity.par.ParOrigemVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="TB_ESOCIAL_EMPREGADOR", schema="ESOCIAL")
//public class TriatVO {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceEmpregador")
//	@SequenceGenerator(name = "sequenceEmpregador", sequenceName = "SEQ_EMPREGADOR" )
//	private Long idEmpregador;
//	private Integer idade;
//	private String nome;
//
//}

@Table(name="TSOC_1000_EMPREGADOR", schema="ESOCIAL")
public class TriatEventoVO {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceSeqId1000")
	@SequenceGenerator(name = "sequenceSeqId1000", sequenceName = "ESOC_SEQ_ID_1000" )
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
	
	
	@ManyToOne
	@JoinColumn(name = "idOrigem")
	private ParOrigemVO origemVO;
	
	@ManyToOne
	@JoinColumn(name = "idPeriodoDet")
	private CtrPeriodoDetVO periodoDetVO;
	
	private String ctrNumCpf;	
	
	@ManyToOne
	@JoinColumn(name = "idLote")
	private CtrLoteVO ctrLoteVO;
	
	@ManyToOne
	@JoinFormula(value="(SELECT C.ID_CODIGO FROM ESOCIAL.TSOC_PAR_CODIGO C WHERE C.COD_PAR = CTR_FLG_STATUS AND C.COD_NUM = '1002')")
	private ParCodigoVO parCodigoCtrFlgStatus;
	
	private String xmlEnvio;	//CLOB
	
	@ManyToOne
	@JoinColumn(name = "idEvento")
	private ParEventoVO eventoVO;
	

	@ManyToOne
	@JoinFormula(value="(SELECT C.ID_CODIGO FROM ESOCIAL.TSOC_PAR_CODIGO C WHERE C.COD_PAR = CTR_FLG_ACAO AND C.COD_NUM = '1001')")
	private ParCodigoVO parCodigoCtrFlgAcao;	
	
	private Integer wsCodResposta;	
	private String wsDscResposta;	
	private String wsDhProc;	
	private String wsVerAppProc;	
	private Character flgVigencia;	
	

	
	
	
}