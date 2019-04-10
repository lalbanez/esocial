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
@Table(name="TSOC_PAR_DEFINICAO", schema="ESOCIAL")
public class ParDefinicaoVO {
	
	@Id
    private Integer idDefinicao;
	private Integer codIns;
	private Integer codNum;
	private String desDescricao;
	private String flgParGlobPart;
	private Date datIng;
	private Date datUltAtu;
	private String nomUsuUltAtu;
	private String nomProUltAtu;
//	 
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mapped)
//    private List<CodigoVO> listaCodigoVO;

}
