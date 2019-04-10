package br.com.esocial.rubricas;

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

@Table(name = "TSOC_PAR_AMBIENTE", schema = "ESOCIAL")
public class Ambiente {

	@Id
	private Integer idAmbiente;
	private Integer tpamb;
	private Integer procemi;
	private String verproc;
	private String flgStatus;
	private Calendar datIng;
	private Calendar datUltAtu;
	private String nomUsuUltAtu;
	private String nomProUltAtu;
}
