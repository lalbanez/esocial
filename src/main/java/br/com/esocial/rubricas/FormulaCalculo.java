package br.com.esocial.rubricas;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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

@Table(name = "TB_FORMULA_CALCULO", schema = "USER_IPESP")
@IdClass(FormulaCalculoPK.class)
public class FormulaCalculo {

	private Integer codIns;
	@Id
	private Integer codRubrica;
	private Integer seqVigRubrica;
	private Integer codFcrubrica;
	private Integer seqVig;
	private String desFc;
	private String tipValor;
	private String flgComp;
	private String tipAplicacao;
	private Integer valUnidade;
	private Calendar datIng;
	private Calendar datUltAtu;
	private String nomUsuUltAtu;
	private String nomProcUltAtu;
	private Calendar datIniVig;
	private Calendar datFimVig;
	private Integer numPrioridade;
	private String mscInformacao;
	private String colInformacao;
	private String aplicaReajOj;
	private String flgAplicaRateio;
	@Id
	private Integer codEntidade;
	private String codContaAnt;
	private String indGravaDetalhe;
	@Column(name = "NUM_PRIORIDADE_133")
	private Integer numPrioridade133;

}
