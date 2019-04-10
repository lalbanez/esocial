package br.com.esocial.rubricas;

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

@Table(name = "VW_RUBRICAS_IR", schema = "USER_IPESP")
public class RubricaIR {

	private Integer codConceito;
	@Id
	private Integer codRubrica;
	private String flgDedIr;
	private String flgBaseIr;
	private String tipEvento;
	private String tipEventoEspecial;
	private String flgDedIrExt;
	private String flgBaseIrExt;

}
