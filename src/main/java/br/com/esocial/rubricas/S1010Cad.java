package br.com.esocial.rubricas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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

@Table(name = "TSOC_CAD_RUBRICA", schema = "ESOCIAL")
public class S1010Cad {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceCadRubrica")
	@SequenceGenerator(name = "sequenceCadRubrica", sequenceName = "ESOC_SEQ_ID_CAD_RUBRICA")
	private Integer idCadRub;
	private Integer seqCad; // Versionamento da rubrica - Sempre atualizar quando houver atualização da
							// rubrica
	private Integer codIns; // 1
	private Integer codRubrica; // Código da Rubrica:
	private String tabRubrica; // Não Existe
	private Calendar datIniVig; // Início Vigência:
	private Calendar datFimVig; // Fim Vigência:
	private String nomRubrica; // Identificador da Rubrica:
	private String flgNatureza; // C - Crédito ou D - Débito
	private String tipEventoEspecial; // TB_RUBRICAS - Coluna TP_EVENTO_ESPECIAL
	private Integer codEntidade; // Menor codEntidade contida em TB_RUBRICAS, cod_entidade para a rubrica em
									// questão
	private String flgDedIr; //
	private String flgBaseIr; //
	private String indGravaDetalhe; //
	private Calendar datIng; // sysdate
	private Calendar datUltAtu; // sysdate
	private String nomUsuUltAtu; // usuarioLogado
	private String nomProUltAtu; // Método
	@Column(name = "FLG_POSSUI_GRUPO_9")
	private String flgPossuiGrupo9; //
	private String flgIncContribRppsMil; // Incidência para Contribuições RPPS/Regime Militar:
	@Column(name = "FLG_POSSUI_GRUPO_N_9")
	private String flgPossuiGrupoN9; //
	
	private Integer codNatRubEsocial; // natrubr
	private Integer codTipRubEsocial; // tprubr
	private Integer codIncCpEsocial; // codinccp
	private Integer codIncIrrfEsocial; // codincirrf
	private Integer codIncFgtsEsocial; // codincfgts
	private Integer codIncSindEsocial; // codincsind
	private Integer codIncCprpEsocial; // codinccprp
	private String flgTetoRemEsocial; // tetoremun
	private String desObsRub; // observacao
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idCadRub")
	private List<ProcessoCadRubrica> listaProcessos;
	
	private String flgInput; // Deve ser setado N para qualquer inclusão/alteracao realizada através da tela

}
