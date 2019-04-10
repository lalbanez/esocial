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

@Table(name = "TSOC_HCAD_RUBRICA", schema = "ESOCIAL")
public class S1010HCad {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceHCadRubrica")
	@SequenceGenerator(name = "sequenceHCadRubrica", sequenceName = "ESOC_SEQ_ID_HCAD_RUBRICA")
	private Integer idHistRub;
	private Integer seqHist;
	private Integer codIns;
	private Integer codRubrica;
	private String tabRubrica;
	private Calendar datIniVig;
	private Calendar datFimVig;
	private String nomRubrica;
	private String flgNatureza;
	private String tipEventoEspecial;
	private Integer codEntidade;
	private String flgDedIr;
	private String flgBaseIr;
	private String indGravaDetalhe;
	private Calendar datIng;
	private Calendar datUltAtu;
	private String nomUsuUltAtu;
	private String nomProUltAtu;
	private Integer idCadRub;
	@Column(name="FLG_POSSUI_GRUPO_9")
	private String flgPossuiGrupo9;
	@Column(name="FLG_POSSUI_GRUPO_N_9")
	private String flgPossuiGrupoN9;
	private String flgIncContribRppsMil;
	
	private Integer codNatRubEsocial; 
	private Integer codTipRubEsocial; 
	private Integer codIncCpEsocial; 
	private Integer codIncIrrfEsocial; 
	private Integer codIncFgtsEsocial; 
	private Integer codIncSindEsocial; 
	private Integer codIncCprpEsocial; 
	private String flgTetoRemEsocial; 
	private String desObsRub; 
	private String flgInput; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCadRub")
	private List<ProcessoHCadRubrica> listaProcessos;
	
}
