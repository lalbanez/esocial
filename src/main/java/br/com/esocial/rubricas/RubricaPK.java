package br.com.esocial.rubricas;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ID CLASS para classe {@link Rubrica}
 * @author Leandro Albanez
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RubricaPK implements Serializable{

	private static final long serialVersionUID = -4031893620231094716L;

	private Integer codRubrica;
	private Integer codEntidade;
}
