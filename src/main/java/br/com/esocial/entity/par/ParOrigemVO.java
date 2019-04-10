package br.com.esocial.entity.par;

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
@Table(name="TSOC_PAR_ORIGEM",schema="ESOCIAL")
public class ParOrigemVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceTsocParOrigem")
	@SequenceGenerator(name = "sequenceTsocParOrigem", sequenceName = "SEQ_TSOC_PAR_ORIGEM" )
	private Long idOrigem;
	private String numCnpj;
    private Integer codIns;
    private Integer idEmpregador;
    private String descOrigem;
    private String nomeAbreviado;
    private String razaoSocial;
    private String numTel;
    private String numCel;
    private String desEmail;
    private String nomLogradouro;
    private String codUf;
    private Integer codMunicipio;
    private Integer codBairro;
    private Integer numCep;
    private String desComplemento;
    private Date datIng;
    private Date datUltAtu;
    private String nomUsuUltAtu;
    private String momProUltAtu;//Todo
    
//    
//	 @ManyToMany(cascade = CascadeType.ALL)
//     @JoinTable(
//            name = "tsoc_Ctr_Periodo_Det", 
//            schema = "ESOCIAL",
//            joinColumns = { @JoinColumn(name = "idOrigem") }, 
//            inverseJoinColumns = { @JoinColumn(name = "idPeriodo") })
//	private List<TsocCtrPeriodoEntity> tsocCtrPeriodoEntity;
//    
    
}
