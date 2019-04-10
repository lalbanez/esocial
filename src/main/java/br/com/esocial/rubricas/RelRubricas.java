package br.com.esocial.rubricas;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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

@Table(name = "TB_REL_RUBRICAS", schema = "USER_IPESP")
@IdClass(RelRubricasPK.class)
public class RelRubricas {

	private Integer codIns;
	@Id
	private Integer codRubrica;
	private String nomRubrica;
	private Integer codConceito;
	@Id
	private Integer codTipInstituicao;
	@Id
	private Integer codTipRelatorio;
	@Id
	private Integer codAgrupacaoRubrica;
	private String flgNatureza;

}
