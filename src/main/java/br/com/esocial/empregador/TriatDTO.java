package br.com.esocial.empregador;

import java.text.ParseException;
import java.util.Calendar;

import org.modelmapper.ModelMapper;

import br.com.esocial.estrutural.util.DataUtil;
import br.com.esocial.estrutural.util.MascaraUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TriatDTO {

	private Integer idEmpregador;
	
	//Identificação do Evento
	private String tpamb;
	private String verproc;  
	private String procemi;  
	
	private String ctrFlgAcao;
	
	//Identificação da Operação das Informações
	//tipo de acao
	private String ideperiodoInivalid; 
	private String ideperiodoFimvalid; 
	
	//Aba Empregador - Informações do Empregador 
	private String tpinsc;    
	private String nrinsc; 
	private String infocadastroNmrazao;    
	private String infocadastroClasstrib;   
	private String infocadastroNatjurid; 
	private Integer infocadastroIndcoop;  //Integer
	private Integer infocadastroIndconstr;  //Integer
	private Integer infocadastroInddesfolha;    //Integer
	private Integer infocadastroIndoptregeletron;    //Integer
	private String infocadastroIndented; 
	private String infocadastroIndett;    
	private String infocadastroNrregett; 
	
	//Aba Empregador - Informações Complementares - Empresas Isentas - Dados da Isenção
	private String dadosisencaoIdeminlei; 
	private String dadosisencaoNrcertif;  
	private Calendar dadosisencaoDtemiscertif;  //Calendar
	private Calendar dadosisencaoDtvenccertif;  //Calendar
	private String dadosisencaoNrprotrenov;  
	private Calendar dadosisencaoDtprotrenov;  //Calendar
	private Calendar dadosisencaoDtdou;         //Calendar
	private Integer dadosisencaoPagdou;   //Integer
	
	//Aba Contato
	private String contatoNmctt; 
	private String contatoCpfctt;   
	private String contatoEmail;
	private String contatoFonecel;  
	private String contatoFonefixo; 
	
	//Aba Órgão Público
	private String infoopIndugrpps; 
	private Integer infoopEsferaop;   //Integer
	private Integer infoopPoderop;   //Integer
	private Integer infoopVrtetorem;   //Integer
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

	//Aba Outras Informações
	private Integer infoorginterIndacordoinsenmul;
	private Integer situacaopjIndsitpj;	 //Integer
	private Integer situacaopfIndsitpf;	//Integer
	
	
	
	public static TriatForm converterDTOparaForm(TriatDTO dto) throws ParseException {
		TriatForm form = new TriatForm();
		if(dto != null) {
			form = new ModelMapper().map(dto, TriatForm.class);
			
			if(dto.getDadosisencaoDtemiscertif() != null) {
				form.setDadosisencaoDtemiscertif(DataUtil.converterCalendarDDMMYYYY(dto.getDadosisencaoDtemiscertif()));
			}
			
			if(dto.getDadosisencaoDtvenccertif() != null) {
				form.setDadosisencaoDtvenccertif(DataUtil.converterCalendarDDMMYYYY(dto.getDadosisencaoDtvenccertif()));
			}
			
			if(dto.getDadosisencaoDtprotrenov() != null) {
				form.setDadosisencaoDtprotrenov(DataUtil.converterCalendarDDMMYYYY(dto.getDadosisencaoDtprotrenov()));
			}
			
			if(dto.getDadosisencaoDtdou() != null) {
				form.setDadosisencaoDtdou(DataUtil.converterCalendarDDMMYYYY(dto.getDadosisencaoDtdou()));
			}
			
			if(dto.getContatoCpfctt() != null) {
				form.setContatoCpfctt(MascaraUtil.mascaraCpf(dto.getContatoCpfctt()));
			}
			
			if(dto.getContatoFonecel() != null) {
				form.setContatoFonecel(MascaraUtil.mascaraTelefone(dto.getContatoFonecel()));
			}
			
			if(dto.getContatoFonefixo() != null) {
				form.setContatoFonefixo(MascaraUtil.mascaraTelefone(dto.getContatoFonefixo()));
			}
			
			if(dto.getSoftwarehouseCnpjsofthouse() != null) {
				form.setSoftwarehouseCnpjsofthouse(MascaraUtil.mascaraCNPJ(dto.getSoftwarehouseCnpjsofthouse()));
			}
			
			if(dto.getSoftwarehouseTelefone() != null) {
				form.setSoftwarehouseTelefone(MascaraUtil.mascaraTelefone(dto.getSoftwarehouseTelefone()));
			}
			
			
			if(dto.getInfoopCnpjefr() != null) {
				form.setInfoopCnpjefr(MascaraUtil.mascaraCNPJ(dto.getInfoopCnpjefr()));
			}
			
		}
		return form;
	}
	
	public static TriatDTO converterFormParaDTO(TriatForm form) throws ParseException {
		TriatDTO dto = new TriatDTO();
		if(form != null) {
			dto = new ModelMapper().map(form, TriatDTO.class);
			//Remover máscaras
			if(!form.getDadosisencaoDtemiscertif().isEmpty()) {
				dto.setDadosisencaoDtemiscertif(DataUtil.obterCalendarSemHorario(form.getDadosisencaoDtemiscertif()));
			}
			if(!form.getDadosisencaoDtemiscertif().isEmpty()) {
				dto.setDadosisencaoDtvenccertif(DataUtil.obterCalendarSemHorario(form.getDadosisencaoDtvenccertif()));
			}
			if(!form.getDadosisencaoDtemiscertif().isEmpty()) {
				dto.setDadosisencaoDtprotrenov(DataUtil.obterCalendarSemHorario(form.getDadosisencaoDtprotrenov()));
			}
			if(!form.getDadosisencaoDtemiscertif().isEmpty()) {
				dto.setDadosisencaoDtdou(DataUtil.obterCalendarSemHorario(form.getDadosisencaoDtdou()));
			}
			
			if(!form.getContatoCpfctt().isEmpty()) {
				dto.setContatoCpfctt(MascaraUtil.removeMascara(form.getContatoCpfctt()));
			}
			
			if(!form.getContatoFonefixo().isEmpty()) {
				dto.setContatoFonefixo(MascaraUtil.removeMascara(form.getContatoFonefixo()));
			}
			
			if(!form.getContatoFonecel().isEmpty()) {
				dto.setContatoFonecel(MascaraUtil.removeMascara(form.getContatoFonecel()));
			}
			
			if(!form.getSoftwarehouseTelefone().isEmpty()) {
				dto.setSoftwarehouseTelefone(MascaraUtil.removeMascara(form.getSoftwarehouseTelefone()));
			}
			
			if(!form.getSoftwarehouseCnpjsofthouse().isEmpty()) {
				dto.setSoftwarehouseCnpjsofthouse(MascaraUtil.removeMascara(form.getSoftwarehouseCnpjsofthouse()));
			}
		}
		
		return dto;
    }
	
	
	public static TriatForm converterDTOparaTriatForm(TriatDTO dto) {
		
		return null;
	}
	
}
