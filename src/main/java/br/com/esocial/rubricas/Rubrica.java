package br.com.esocial.rubricas;

import java.io.Serializable;
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

@Table(name = "TB_RUBRICAS", schema = "USER_IPESP")
@IdClass(RubricaPK.class)
public class Rubrica implements Serializable{
	
	private static final long serialVersionUID = -7572097928625464511L;
	
	private Integer codIns;
	private Integer codConceito;
	private Integer seqVigConceito;
	@Id
	private Integer codRubrica;
	private Integer seqVig;
	private String nomRubrica;
	private String flgNatureza;
	private String flgQuota;
	private String tipEvento;
	private String tipEventoEspecial;
	private Calendar datIng;
	private Calendar datIniVig;
	private Calendar datFimVig;
	private String nomUsuUltAtu;
	private String nomProUltAtu;
	private Integer numPrioridade;
	private Integer codRubricaContraria;
	private String flgBaseIr;
	private String flgRateio;
	private String flgDedIr;
	private String flgContr;
	private String tipComposicao;
	private String excDespPrev;
	@Id
	private Integer codEntidade;
	private String flgExtraFolha;
	private Integer codAgrupa;
	@Column(name="OP_INCID_PA_13")
	private String opIncidPa13;
	private String flgIncorporacao;
	private String flgIndCdr;
	private String flgIndFev;
	private String flgMuda_base;
	private String flgEc70;
	private String flgHabilitaCodCargoIncorp;
	private String flgHabilitaSalRefIncorp;
	private String flgHabilitaDtIncorp;
	private String flgHabilitaCodFuncaoIncorp;
	private Calendar datUltAtu;
	private String codComplemento;
	private String flgHabilitaTabelaIncorp;
	private String flgRubGeralMiniFolha;
	private String flgParticipaRetroativo;
	private String tipLancamento;
	private String tipBeneficio;
	private String flgDedIrExt;
	private String flgBaseIrExt;
	@Column(name="FLG_HABILITA_SAL_REF2_INCORP")
	private String flgHabilitaCodCargo2Incorp;
	@Column(name="FLG_HABILITA_COD_CARGO2_INCORP")
	private String flgHabilitaSalRef2Incorp;
	private Calendar datIngObs;
	private Calendar datUltAtuObs;
	private String flgOrigem;
	private String dscObservacao;
	private String flgBaseIndiv;
	private String rubAgrup;
	private String tipBeneficioFluxo;
	private String tipPagamento;
	private String flgHabilita_calc_serv;
	private String flgAplicaPropPensao;
	private String dscLegislacao;
	private String dscFormaLancamento;
	private String flgDuplica;
}
