package br.com.esocial.estrutural.util;

import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	
    /**
     * Esse método recebe um array, e dependendo do tamanho dele, retorna uma String com os valores de "?" para 
     * ser utilizado em query.
     * Exemplo com nenhum registro ou [0]  = retorna "";
     * Exemplo com tamanho [1]             = (?)
     * Exemplo com tamanho [2]             = (?,?)
     * Exemplo com tamanho [3]             = (?,?,?)
     */
    public static String obterInterrogacoesParametros(String[] array){
    	StringBuilder sb = new StringBuilder();
    	
    	if(array != null && array.length != 0){
    	    sb.append("(");
     	        for(int contador = 0; contador < array.length; contador++){
		    if (contador > 0){
		        sb.append(", ");
		    }
		    sb.append("?");
		}
    	    sb.append(")");
    	}
        return sb.toString();
    }
    
    public static String obterInterrogacoesParametros(List<String> lista){
    	return obterInterrogacoesParametros((String[])lista.toArray());
    }
    
    
	public static String retiraMascaraCpf(String cpf) {
	    if (cpf == null) {
	        return "";
	    }
	    else if (cpf.trim().equals("")) {
	        return "";
	    }
	    else {
			return cpf.replace('.', '-').replaceAll("-", "");
	    }
	}
	
    //https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    public static boolean isCPF(String cpfEntrada) {
    	
    	String CPF =  retiraMascaraCpf(cpfEntrada);
    	
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {              
            num = (int)(CPF.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); 
          
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);
          
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }
    
    public static boolean isEmailValido(String email) {
    	Matcher matcher;
    	Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);
    	matcher = pattern.matcher(email);
		return matcher.matches();
    }
    
          
}
