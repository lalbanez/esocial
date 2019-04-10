package br.com.esocial.entity.par;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TSOC_PAR_SIGEPREV_ESOCIAL", schema = "ESOCIAL")
public class ParSigePrevEsocialVO {

	@Id
//	private Integer idCodDepara;
//	private Integer codIns;
//	private String descricao;
//	private String codSigeprev;
//	private String desSigeprev;
	private String codEsocial;
	private String desEsocial;
	private Integer codTipo;
//	private Calendar datIng;
//	private Calendar datUltAtu;
//	private String nomUsuUltAtu;
//	private String nomProUltAtu;

}
