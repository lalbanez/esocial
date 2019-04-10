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
@Table(name="TSOC_PAR_LOTE",schema="ESOCIAL")
public class ParLoteVO {

	@Id
	private Integer	idParLote;
	private Integer	codIns;
	private Integer	qtdMaxEventos;
	private String	xmlns;
	private String	descElemento;
	private Calendar	datIniVig;
	private Calendar	datFimVig;
	private Calendar	datIng;
	private Calendar	datUltAtu;
	private String	nomUsuUltAtu;
	private String	nomProUltAtu;
	private Integer	consultaDelayMin;
}
