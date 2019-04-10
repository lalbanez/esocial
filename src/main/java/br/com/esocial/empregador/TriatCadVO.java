package br.com.esocial.empregador;


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

//@Table(name="TSOC_1000_EMPREGADOR", schema="ESOCIAL") ESOC_SEQ_ID_CAD_EMPREGADOR
@Table(name="TSOC_CAD_EMPREGADOR", schema="ESOCIAL")
public class TriatCadVO {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceEmpregador")
//	@SequenceGenerator(name = "sequenceEmpregador", sequenceName = "SEQ_EMPREGADOR" )
//	private Long idEmpregador;
//	private Integer idade;
//	private String nome;
//	
	
	private Integer codIns;	
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
		
	
	private String softwarehouseCnpjsofthouse;	
	private String softwarehouseNmrazao;	
	private String softwarehouseNmcont;	
	private String softwarehouseTelefone;	
	private String softwarehouseEmail;	
	
	private Integer infoorginterIndacordoinsenmul;
	private Integer situacaopjIndsitpj;	
	private Integer situacaopfIndsitpf;	
	
	private String novavalidadeInivalid;
	private String novavalidadeFimvalid;	
	private Calendar datIng;	
	private Calendar datUltAtu;	
	private String nomUsuUltAtu;	
	private String nomProUltAtu;
	@Id
	private Integer idEmpregador;

}