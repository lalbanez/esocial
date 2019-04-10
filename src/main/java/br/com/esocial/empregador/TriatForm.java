package br.com.esocial.empregador;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TriatForm {
	
	private String idEmpregador;
	
	private String periodoAberto;
	private String tipoPeriodo;
	private String idEventoSelecionado;
	
	@NotEmpty(message = "* O campo Tipo de Ação deve ser preenchido.")
	private String ctrFlgAcao;
	
	private String codIns;
	
	//Identificação do Evento
	@NotEmpty(message = "* O campo Identificação do Ambiente deve ser preenchido.")
	private String tpamb;
	@NotEmpty(message = "* O campo Versão do Processo de Emissão do Evento deve ser preenchido.")
	private String verproc;  
	@NotEmpty(message = "* O campo Processo de Emissão do Evento deve ser preenchido.")
	private String procemi;  
	
	//Identificação da Operação das Informações
	//tipo de acao
	private String ideperiodoInivalid; 
	private String ideperiodoFimvalid; 
	
	
	//Aba Empregador - Informações do Empregador 
	@NotEmpty(message = "* O campo Tipo de Inscrição deve ser preenchido.")
	private String tpinsc;    
	@NotEmpty(message = "* O campo Número Inscrição deve ser preenchido.")
	private String nrinsc; 
	@NotEmpty(message = "* O campo Nome Razão Social deve ser preenchido.")
	private String infocadastroNmrazao;    
	@NotEmpty(message = "* O campo Classificação Tributária deve ser preenchido.")
	private String infocadastroClasstrib;   
	private String infocadastroNatjurid; 
	private String infocadastroIndcoop;  //
	private String infocadastroIndconstr;  //
	@NotEmpty(message = "* O campo Desoneração da Folha deve ser preenchido.")
	private String infocadastroInddesfolha;   //
	@NotEmpty(message = "* O campo Ponto Eletrônico deve ser preenchido.")
	private String infocadastroIndoptregeletron;    
	private String infocadastroIndented; //
	@NotEmpty(message = "* O campo Empresa de Trabalho Temporário da Folha deve ser preenchido.")
	private String infocadastroIndett;    
	private String infocadastroNrregett; 
	
	//Aba Empregador - Informações Complementares - Empresas Isentas - Dados da Isenção
	private String dadosisencaoIdeminlei; 
	private String dadosisencaoNrcertif;  
	private String dadosisencaoDtemiscertif;  //data
	private String dadosisencaoDtvenccertif;  //data
	private String dadosisencaoNrprotrenov;  
	private String dadosisencaoDtprotrenov;  //data
	private String dadosisencaoDtdou;         //data
	private String dadosisencaoPagdou;  
	
	//Aba Contato
	private String contatoNmctt; 
	private String contatoCpfctt;   
	private String contatoEmail;
	private String contatoFonecel;  
	private String contatoFonefixo; 
	
	//Aba Órgão Público
	private String infoopIndugrpps; 
	private String infoopEsferaop;  //
	private String infoopPoderop;  //
	private String infoopVrtetorem;  //
	private String infoopIdeefr; 
	private String infoopCnpjefr; 
	
	private String infoefrIdeefr;	
	private String infoefrCnpjefr;	
	
	
	//Aba Software
	private String softwarehouseNmrazao;
	private String softwarehouseCnpjsofthouse; 
	private String softwarehouseTelefone; 
	private String softwarehouseNmcont;  
	private String softwarehouseEmail; 
	
	// Aba Outras Informações
	private String infoorginterIndacordoinsenmul;
	private String situacaopjIndsitpj;	
	private String situacaopfIndsitpf;	
	
}
