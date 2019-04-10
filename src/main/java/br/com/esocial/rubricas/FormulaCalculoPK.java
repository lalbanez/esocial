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
public class FormulaCalculoPK implements Serializable{

	private static final long serialVersionUID = 4746880355212505973L;
	
	private Integer codEntidade;
	private Integer codRubrica;
}
