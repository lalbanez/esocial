package br.com.esocial.estrutural.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

////import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


/**
 * Reflection API
 * Autor : Renan Watanabe, 24/09/2018
 */
public class ReflectionUtil {
	
//	private static final Logger logger = LogManager.getLogger(ReflectionUtil.class);

	private static Class<?> noParams[] = {};
	
	//TODO deve-se realizar testes de sincronismos de requisicoes http.
	public static String obterCamposValores(Object objeto) {
		List<String> listaCampoValor = new ArrayList<String>();
		
		String nomeAtributo         = null;
		String nomeGetter           = null;
		String valor          = null;
			
				Class<?> tipoClasse  = objeto.getClass();
				LinkedHashSet<String> listaFields = obterTodosFieldsHerancaRecursivamente(new LinkedHashSet<String>() , tipoClasse);
				LinkedHashSet<String> listaMethods = obterTodosMetodosHerancaRecursivamente(new LinkedHashSet<String>(), tipoClasse);
				
				for(String field : listaFields){
					try{
						nomeAtributo = field;
						nomeGetter  = obterNomeMetodoGetter(nomeAtributo, listaMethods);
						valor = obterValor(objeto.getClass(), objeto, nomeGetter);
						listaCampoValor.add(nomeAtributo + ": " + valor);
						
					}catch(Exception e){
//						logger.error("[ReflectionUtil] Erro ao tentar acessar o atributo: " + nomeAtributo);
					}
				}
			return listaCampoValor.toString();
	}
	
	
private static LinkedHashSet<String> obterTodosFieldsHerancaRecursivamente(LinkedHashSet<String> listaFields, Class<?> classe){
	    
	    Field[] fields = classe.getDeclaredFields();
	    for(Field field : fields){
		listaFields.add(field.getName());
	    }
		
	    if (classe.getSuperclass() != null) {
	    	listaFields = obterTodosFieldsHerancaRecursivamente(listaFields, classe.getSuperclass());
	    }
	    return listaFields;
	}
	
	
	private static LinkedHashSet<String> obterTodosMetodosHerancaRecursivamente(LinkedHashSet<String> listaMethods, Class<?> classe){
		Method[] arrayMetodosExistentes = classe.getDeclaredMethods();
		for(Method method : arrayMetodosExistentes){
			listaMethods.add(method.getName());
		}
		
		if (classe.getSuperclass() != null) {
			listaMethods = obterTodosMetodosHerancaRecursivamente(listaMethods, classe.getSuperclass());
		}
		return listaMethods;
	}
	
	
	
	
	private static String obterNomeMetodoGetter(String nomeAtributo, Set<String> listaMetodosExistentes){
		String nomeUtilizadoParaMetodo = obterNomeUtilizadoParaMetodo(nomeAtributo);
		
		if(listaMetodosExistentes.contains("get" + nomeUtilizadoParaMetodo)){
			return "get" + nomeUtilizadoParaMetodo;
		}else if(listaMetodosExistentes.contains("is" + nomeUtilizadoParaMetodo)){
			return "is" + nomeUtilizadoParaMetodo;
		}else{
			return null;
		}
	}
	
	
	private static String obterValor(Class<?> classe, Object objeto, String nomeMetodo) throws Exception{
		Method metodo = classe.getMethod(nomeMetodo, noParams);
		String valor = "";
		
		if(metodo == null){
			if (classe.getSuperclass() != null) {
				valor = obterValor(classe.getSuperclass(), objeto, nomeMetodo);
			}
		}else{
			valor = (String) metodo.invoke(objeto);
		}
		return valor;
	}
	
	
	private static String obterNomeUtilizadoParaMetodo(String nomeAtributo){
		return nomeAtributo.substring(0,1).toUpperCase() + nomeAtributo.substring(1);
	}
	
}
