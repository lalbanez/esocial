package br.com.esocial.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.esocial.form.CalculadoraForm;

@Controller
@RequestMapping("/calculadora")
public class CalculadoraController {

	private final String TELA_CALCULADORA = "pages/monitoramento/calculadora";

	@InitBinder("calculadoraForm")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new CalculadoraValidator());
    }
	
	@GetMapping("")
	public String iniciar() {
		return TELA_CALCULADORA;
	}

	@ModelAttribute("calculadoraForm")
	public CalculadoraForm popularForm() {
		return new CalculadoraForm();
	}

	@PostMapping("/teste")
	public String teste(Model mv, @Valid CalculadoraForm form, BindingResult bindingResult) {
		form.setResultado(2000);
		return TELA_CALCULADORA;
	}
	
	@PostMapping("/soma")
	public String soma(Model mv, @Valid CalculadoraForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TELA_CALCULADORA;
		}
		
		form.setResultado(Integer.parseInt(form.getNumero1()) + Integer.parseInt(form.getNumero2()));
		return TELA_CALCULADORA;
	}
	
	@PostMapping("/subtracao")
	public String subtracao(Model mv, @Valid CalculadoraForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TELA_CALCULADORA;
		}
		
		form.setResultado(Integer.parseInt(form.getNumero1()) - Integer.parseInt(form.getNumero2()));
		return TELA_CALCULADORA;
	}
	
	@PostMapping("/multiplicacao")
	public String multiplicacao(Model mv, @Valid CalculadoraForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TELA_CALCULADORA;
		}
		
		form.setResultado(Integer.parseInt(form.getNumero1()) * Integer.parseInt(form.getNumero2()));
		return TELA_CALCULADORA;
	}
	
	@PostMapping("/divisao")
	public String divisao(Model mv, @Valid CalculadoraForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TELA_CALCULADORA;
		}
		
		form.setResultado(Integer.parseInt(form.getNumero1()) / Integer.parseInt(form.getNumero2()));
		return TELA_CALCULADORA;
	}
	
	public class CalculadoraValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return CalculadoraForm.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			CalculadoraForm form = (CalculadoraForm) target;

			//Segunda Validação
			if (!form.getNumero1().isEmpty()) {
				if (!form.getNumero1().matches("^(0|[1-9][0-9]*)$")) {
					errors.rejectValue("numero1", "", "* O campo deve ser numérico.");
				}
			}

			if (!form.getNumero2().isEmpty()) {
				if (!form.getNumero2().matches("^(0|[1-9][0-9]*)$")) {
					errors.rejectValue("numero2", "", "* O campo deve ser numérico.");
				}
			}
			
			//Terceira Validação
			if(!errors.hasErrors()) {
				if (form.getNumero1().length() > 5) {
					errors.rejectValue("numero1", "", "* O campo deve conter até 5 números.");
				}
				
				if (form.getNumero2().length() > 5) {
					errors.rejectValue("numero2", "", "* O campo deve conter até 5 números.");
				}
			}
			
		}

	}
	
}
