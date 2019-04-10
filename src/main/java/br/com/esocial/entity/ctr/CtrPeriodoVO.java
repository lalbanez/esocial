package br.com.esocial.entity.ctr;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="TSOC_CTR_PERIODO",schema="ESOCIAL")
public class CtrPeriodoVO {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceTsocCtrPeriodo")
	@SequenceGenerator(name = "sequenceTsocCtrPeriodo", sequenceName = "SEQ_TSOC_CTR_PERIODO" )
	private Integer idPeriodo;
	
	private Integer codIns;
	
	private String periodo;
	
	private String flgStatus;
	
	private Date datAbertura;
	
	private Date datFechamento;
	
	private String tipoPeriodo;
	
	private Date datIng;
	
	private Date datUltAtu;
	
	private String nomUsuUltAtu;
	
	private String nomProUltAtu;
	
//	
//	 @OneToMany(cascade = CascadeType.ALL,
//	            fetch = FetchType.LAZY,
//	            mappedBy = "tsocCtrPeriodoEntity")
//	private List<TsocCtrPeriodoDetEntity> listaTsocCtrPeriodoDetEntity;
	 
	 
//	 @ManyToMany(cascade = CascadeType.ALL)
//	    @JoinTable(
//	            name = "tsocCtrPeriodoDetEntity", 
//	            joinColumns = { @JoinColumn(name = "idPeriodo") }, 
//	            inverseJoinColumns = { @JoinColumn(name = "idOrigem") }
//	        ) 
//	private List<TsocParOrigemEntity> listaTsocParOrigemEntity;
//	
}
