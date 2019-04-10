package br.com.esocial.form;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import br.com.esocial.rubricas.ProcessoCadRubrica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class S1010Form {
	
	/* Parametros de Cadastro*/
	private String periodoAberto;
	private String tipoPeriodo;

	
	/* Dados Ultimo Evento Gerado */
	private String idEventoUltEvGerado;
	private String dtGeracaoUltEvGerado;
	private String tipoAcaoUltEvGerado;
	private String iniVigenciaUltEvGerado;
	private String fimVigenciaUltEvGerado;
	private String statusEventoUltEvGerado;
	private String numLoteUltEvGerado;
	private String dataEnvioUltEvGerado;
	private String numProtocoloEnvioUltEvGerado;
	private String numReciboUltEvGerado;
	private String dataRetornoUltEvGerado;
	

	 /*Identificacao do Evento*/
	private String identificacaoAmbiente;
	private String versaoProcessoEmissaoEvento;
	private String processoEmissaoEvento;

	/*Identificação do Empregador*/
	private String tipoInscricao;
	private String numInscricao;
	
	/*Identificação da Operação das Informações*/
	@NotBlank(message = "* O campo deve estar preenchido")
	private String tipoAcaoOperacao;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String iniVigenciaOperacao;
	
	private String fimVigenciaOperacao;
	
	/*Dados da Rubrica*/

	@NotBlank(message = "* O campo deve estar preenchido")
	private String codRubrica;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String idRubrica;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String natureza;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String tipoRubrica;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String incidenciaPrevSocial;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String incidenciaIRRF;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String incidenciaFGTS;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String incidenciaContribSindical;

	@NotBlank(message = "* O campo deve estar preenchido")
	private String incidenciaContribRPPS;
	
	@NotBlank(message = "* O campo deve estar preenchido")
	private String tetoRemuneratorio;
	
	private String observacao;
	
	//Inclusão de Processo
	
	private List<ProcessoCadRubrica> listaProcesso;
	
	private String idProcesso;
	
	private String tipoProcesso;
	
	private String numProcesso;
	
	private String extensaoDecisao;
	
	private String codSuspensao;
	
	private String idRubricaConsulta;
	
	private String isDetalhe;

}
