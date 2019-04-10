package br.com.esocial.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitoramentoForm {
	
	private String empregador;
	
//    @Length(min = 5, message = "*Your nick must have at least 5 characters")
    @NotEmpty(message = "* O campo Origem deve ser preenchido.")
	private String origem;
    
    @NotEmpty(message = "* O campo Período deve ser preenchido.")
	private String periodo;
    
	private String tipoEvento;
	private String evento;
	
}
