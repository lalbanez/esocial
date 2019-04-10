package br.com.esocial.rubricas;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelRubricasPK implements Serializable{

	private static final long serialVersionUID = -59400245433249720L;
	
	private Integer codRubrica;
	private Integer codTipInstituicao;
	private Integer codTipRelatorio;
	private Integer codAgrupacaoRubrica;
}
