package br.com.esocial.entity.par;

import java.sql.Date;

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
@Table(name="TSOC_PAR_CODIGO", schema="ESOCIAL")
public class ParCodigoVO {
	
	@Id
	private Integer idCodigo;
	private Integer codIns;
	private Integer codNum;
	private String codPar;
	private String desDescricao;
	private String desDescricaoCurta;
	private Integer numOrdem;
	private String flgVigente;
	private Date datIng;
	private Date datUltAtu;
	private String nomUsuUltAtu;
	private String nomProUltAtu;
	private Integer idDefinicao;
	


}
