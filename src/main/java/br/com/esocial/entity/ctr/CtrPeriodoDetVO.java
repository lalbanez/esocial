package br.com.esocial.entity.ctr;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.esocial.entity.par.ParOrigemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TSOC_CTR_PERIODO_DET",schema="ESOCIAL")
public class CtrPeriodoDetVO {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceTsocCtrPeriodoDet")
	@SequenceGenerator(name = "sequenceTsocCtrPeriodoDet", sequenceName = "SEQ_TSOC_CTR_PERIODO_DET" )
	private Integer idPeriodoDet;
	
	@ManyToOne
	@JoinColumn(name = "idPeriodo", nullable = false)
	private CtrPeriodoVO periodoVO;
	
	@ManyToOne
	@JoinColumn(name = "idOrigem", nullable = false)
	private ParOrigemVO origemVO;
	
	private Integer codIns;
	private Date datIng;
	private Date datUltAtu;
	private String nomUsuUltAtu;
	private String nomProUltAtu;
	private Integer idEvento;
	private String flgStatus;
	
	

}
