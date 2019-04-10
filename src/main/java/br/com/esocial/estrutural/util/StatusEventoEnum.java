package br.com.esocial.estrutural.util;

import java.util.Arrays;
import java.util.List;

public enum StatusEventoEnum {
	
	
	PRE_ENVIO_EVENTOS_GERADOS("Pré-envio", "Eventos Gerados", "Pré-Envio - Eventos Gerados"){
		public List<String> obterListaTipoStatus(){
			return Arrays.asList(new String[]{"AX"});
		}
		public List<String> obterListaDescStatus() {
			return Arrays.asList(new String[]{"Aguardando geração de XML"});
		}
	},
	
	PRE_ENVIO_EVENTOS_VALIDADOS("Pré-envio", "Eventos Validados", "Pré-Envio - Eventos Validados"){
		public List<String> obterListaTipoStatus(){
			return Arrays.asList(new String[]{"AA","AL"});
		}
		public List<String> obterListaDescStatus() {
			return Arrays.asList(new String[]{"Aguardando Assinatura", "Aguardando Geração do Lote"});
		}
	},
	
	PRE_ENVIO_EVENTOS_COM_ERRO("Pré-envio","Eventos com Erro", "Pré-Envio - Eventos Com Erro"){
		public List<String> obterListaTipoStatus(){
			return Arrays.asList(new String[]{"EV", "EX", "EA", "EL"});
		}
		public List<String> obterListaDescStatus() {
			return Arrays.asList(new String[]{"Erro de Validação", "Erro na geração de XML", "Erro na Assinatura", "Erro na Geração do Lote"});
		}
	},
	
	ENVIO_AGUARDANDO_ENVIO("Envio", "Aguardando Envio", "Envio - Aguardando Envio"){
		public List<String> obterListaTipoStatus(){
			return Arrays.asList(new String[]{"AE"});
		}
		public List<String> obterListaDescStatus() {
			return Arrays.asList(new String[]{"Aguardando Envio"});
		}
	},
	
	ENVIO_EVENTOS_ENVIADOS("Envio", "Eventos Enviados", "Envio - Eventos Enviados"){
		public List<String> obterListaTipoStatus(){
			return Arrays.asList(new String[]{"AR"});
		}
		public List<String> obterListaDescStatus() {
			return Arrays.asList(new String[]{"Enviado"});
		}
	},
	
	ENVIO_EVENTOS_COM_ERRO("Eventos","Eventos com Erro", "Eventos - Eventos Com Erro"){
		public List<String> obterListaTipoStatus(){
			return Arrays.asList(new String[]{"EE", "RE"});
		}
		public List<String> obterListaDescStatus() {
			return Arrays.asList(new String[]{"Erro no Envio", "Retornado com Erro"});
		}
	},
	
	ENVIO_EVENTOS_FINALIZADOS("Eventos","Eventos Finalizados", "Eventos - Eventos Finalizados"){
		public List<String> obterListaTipoStatus(){
			return Arrays.asList(new String[]{"FN"});
		}
		public List<String> obterListaDescStatus() {
			return Arrays.asList(new String[]{"Finalizado"});
		}
	};


	private String descricaoGrupo;
	private String descricaoStatus;
	private String descricaoGrupoStatus;

	private StatusEventoEnum(String descricaoGrupo, String descricaoStatus,String descricaoGrupoStatus) {
	    this.descricaoGrupo = descricaoGrupo;
	    this.descricaoStatus = descricaoStatus;
	    this.descricaoGrupoStatus = descricaoGrupoStatus;
	}
	
	public static StatusEventoEnum obterStatusEventoPorStatus(String descricaoStatus){
		StatusEventoEnum statusEventoSelecionado = null;
		
	    if(descricaoStatus == null || descricaoStatus.isEmpty()){
		    return statusEventoSelecionado;
	    }
	    String nomeStatusEvento = descricaoStatus.toLowerCase();
	    for(StatusEventoEnum statusEvento : StatusEventoEnum.values()){
	       if(statusEvento.getDescricaoStatus().toLowerCase().equals(nomeStatusEvento)){
	    	   statusEventoSelecionado = statusEvento;
	       }
	    }
	    return statusEventoSelecionado;
	}
	
	
	public static StatusEventoEnum obterStatusEventoPorGrupoStatus(String descricaoGrupoStatus){
		StatusEventoEnum statusEventoSelecionado = null;
		
	    if(descricaoGrupoStatus == null || descricaoGrupoStatus.isEmpty()){
		    return statusEventoSelecionado;
	    }
	    String nomeStatusEvento = descricaoGrupoStatus.toLowerCase();
	    for(StatusEventoEnum statusEvento : StatusEventoEnum.values()){
	       if(statusEvento.getDescricaoGrupoStatus().toLowerCase().equals(nomeStatusEvento)){
	    	   statusEventoSelecionado = statusEvento;
	       }
	    }
	    return statusEventoSelecionado;
	}
	
	
	

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}
	public String getDescricaoStatus() {
		return descricaoStatus;
	}
	public String getDescricaoGrupoStatus() {
		return descricaoGrupoStatus;
	}
	public List<String> obterListaTipoStatus(){
		throw new  UnsupportedOperationException();
	}
	public List<String> obterListaDescStatus(){
		throw new  UnsupportedOperationException();
	}
}
