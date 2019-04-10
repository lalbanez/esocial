package br.com.esocial.estrutural.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class MascaraUtil {
	
	public static String retiraMascaraCpf(String cpf) {
	    if (cpf == null  || cpf.trim().equals("")) {
	        return "";
	    }else {
			return cpf.replace('.', '-').replaceAll("-", "").trim();
	    }
	}
	

	public static String formatarNumeroComPontoTresCasas(Integer numero) {
		if(numero == null) {
			return "";
		}
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator('.');
		
		DecimalFormat df = new DecimalFormat();
		df.setDecimalFormatSymbols(symbols);
		df.setGroupingSize(3);
		return df.format(numero);
	}
	
	
	public static String mascaraTelefone(String soNumeros) {
		String numeroMascara = soNumeros;
		
		if(numeroMascara != null && !numeroMascara.isEmpty()) {
			if(numeroMascara.length() == 11) { //Celular
				numeroMascara = "(0" + numeroMascara.substring(0, 2) + ")" + numeroMascara.substring(2, 7) + "-" + numeroMascara.substring(7, 11);
			}else if(numeroMascara.length() == 10) { //Telefone
				numeroMascara = "(0" + numeroMascara.substring(0, 2) + ")" + numeroMascara.substring(2, 6) + "-" + numeroMascara.substring(6, 10);
			}else {
				//do nothing.
			}
		}
		return numeroMascara;
	}
	
	
	public static String mascaraCpf(String soNumeros) {
		String numeroMascara = soNumeros;
		
		if(numeroMascara != null && !numeroMascara.isEmpty()) {
			if(numeroMascara.length() == 11) { //Celular
				numeroMascara =  numeroMascara.substring(0, 3) + "." + numeroMascara.substring(3, 6) + "." + numeroMascara.substring(6, 9) + "-" + numeroMascara.substring(9, 11);
			}else {
				//do nothing.
			}
		}
		return numeroMascara;
	}
	
	public static String mascaraCNPJ(String numero) {
		String numeroMascara = numero;
		
		if(numeroMascara != null && !numeroMascara.isEmpty()) {
			if(numeroMascara.length() == 14) { //Celular
				numeroMascara = numeroMascara.substring(0, 2) + "." + numeroMascara.substring(2, 5) + "." + numeroMascara.substring(5, 8) + "/" +  numeroMascara.substring(8, 12) + "-" + numeroMascara.substring(12, 14);  
			}else {
				//do nothing.
			}
		}
		return numeroMascara;
	}
	
	
	
	
	//Utils.java Sigeprev classe
    public final static Locale BRASIL = new Locale("pt", "BR");
    public final static Locale USA = Locale.US;
    public final static String MASK_DECIMAL = "#,##0.00";
    
    public static String formataDecimalBrasil(String num) {
    	return formataNumero(num, MASK_DECIMAL, BRASIL);
    }

//    public static String formataDecimal(String num) {
//	num = tiraPonto(num);
//	return formataNumero(num, MASK_DOUBLE, USA);
//    }
    
    public static String formataNumero(String num, String mask, Locale locale) {
		if (num == null || num.trim().equals("")) {
		    return "";
		}
		NumberFormat nf = NumberFormat.getInstance(locale);
		DecimalFormat decFormat = (DecimalFormat) nf;
		decFormat.applyPattern(mask);
		String aux;
		try {
		    aux = decFormat.format(Double.parseDouble(num));
		} catch (NumberFormatException n) {
		    aux = "0";
		}
		return aux;
    }
    
	public static String removeMascara(String valor) {
		return valor.replace("(", "")
				    .replace(")", "")
				    .replace("-", "")
				    .replace("_", "")
				    .replace(".", "")
				    .replace(" ", "")
				    .replace("/", "")
				    .replace("", "");
	}
}
