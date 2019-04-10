package br.com.esocial.form;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraForm {

	@NotBlank(message = "* O campo deve estar preenchido")
	private String numero1;

	@NotBlank(message = "* O campo deve estar preenchido")
	private String numero2;

	private int resultado;

	private String operacao;

}
