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
@Table(name="TSOC_PAR_EVENTO", schema="ESOCIAL")
public class ParEventoVO {

	@Id
	private Integer idEvento;
	private String codEvento;
	private String nomTabela;
	private String descEvento;
	private Date datIng;
	private Date datUltAtu;
	private String nomUsuUltAtu;
	private String nomProUltAtu;
	private String tipEvento;
	private Integer codIns;
	private String xmlns;
}
