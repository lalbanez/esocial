package br.com.esocial.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.esocial.estrutural.util.MascaraUtil;
import br.com.esocial.form.MonitoramentoConsultaDTO;
import br.com.esocial.form.MonitoramentoEventoDTO;
import br.com.esocial.repository.MonitoramentoDAO;

@Service
public class MonitoramentoConsultaService {

	@Autowired
	private MonitoramentoDAO monitoramentoDAO;
	@Autowired
	private MonitoramentoService monitoramentoService;
	
	/**
	 * Formatação para a tela de Consulta 
	 */
	public List<MonitoramentoEventoDTO> pesquisarEventoListaPor(String origem, String periodo, String tipoEvento, String evento, String descStatus){
		
		MonitoramentoEventoDTO eventoDTO = monitoramentoService.pesquisarEventosPor(origem, periodo, tipoEvento, evento).get(0);
		
		List<MonitoramentoEventoDTO> listaMonitoramento = new ArrayList<MonitoramentoEventoDTO>();
			
			switch(descStatus) {
				case "Pré-Envio - Eventos Gerados":
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Aguardando geração de XML", eventoDTO.getAguardandoGeracaoXml());
					break;
				case "Pré-Envio - Eventos Validados":
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Aguardando Assinatura", eventoDTO.getAguardandoAssinatura());
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Aguardando Geração do Lote", eventoDTO.getAguardandoGeracaoLote());
					break;
				case "Pré-Envio - Eventos Com Erro":
			     	adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Erro de Validação", eventoDTO.getErroValidacao());
			    	adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Erro na geração de XML", eventoDTO.getErroGeracaoXml());
			     	adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Erro na Assinatura", eventoDTO.getErroAssinatura());
			    	adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Erro na Geração do Lote", eventoDTO.getErroGeracaoLote());
				    break;
				case "Envio - Aguardando Envio":
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Aguardando Envio", eventoDTO.getAguardandoEnvio());
					break;
				case "Envio - Eventos Enviados":
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Enviado", eventoDTO.getEnviado());
					break;				
				case "Eventos - Eventos Com Erro": 
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Erro no Envio", eventoDTO.getErroNoEnvio());
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Retornado com Erro", eventoDTO.getRetornadoComErro());
					break;		
				case "Eventos - Eventos Finalizados":
					adicionarStatusNaLista(listaMonitoramento, eventoDTO, "Erro no Envio", eventoDTO.getFinalizado());
					break;	
		     }
			
			return listaMonitoramento;
		
	}
	
	public List<MonitoramentoConsultaDTO> pesquisarDetalhes(String origem, String periodo, String evento, String idEvento, String numLote, 
			String dataEnvio, String protocoloEnvio, String dataRetorno, String numRecibo, String dataGeracao, String numCpf, String status, String agrupacaoStatus){
		return monitoramentoDAO.pesquisarDetalhesEventoConsulta(origem, periodo, evento, idEvento, numLote, dataEnvio, protocoloEnvio,
				dataRetorno, numRecibo, dataGeracao, numCpf, status, agrupacaoStatus);
	}
	
	
	
	
	private void adicionarStatusNaLista(List<MonitoramentoEventoDTO> listaMonitoramento, MonitoramentoEventoDTO eventoDTO, String descStatus, String totalDescStatus) {
		MonitoramentoEventoDTO eventoItem = new MonitoramentoEventoDTO(); 
		BeanUtils.copyProperties(eventoDTO, eventoItem);
		eventoItem.setDescStatusEvento(descStatus);
		eventoItem.setTotalStatusEvento(MascaraUtil.formatarNumeroComPontoTresCasas(Integer.parseInt(totalDescStatus)));
		listaMonitoramento.add(eventoItem);
	}
	
	
//	public List<MonitoramentoConsultaDTO> pesquisarDetalhesEventos(){
//		
//		
//		
//	}
	
	//TODO
	public LinkedHashMap<String, String> obterListaStatusPor(String agrupacaoStatus){
		LinkedHashMap<String, String> mapStatus = new LinkedHashMap<String, String>();
		
		switch(agrupacaoStatus) {
		    case "Pré-Envio - Eventos Gerados":
		    	mapStatus.put("AX", "Aguardando geração de XML");
		    	break;
			case "Pré-Envio - Eventos Validados":
				mapStatus.put("AA","Aguardando Assinatura");
				mapStatus.put("AL", "Aguardando Geração do Lote");
				break;
			case "Pré-Envio - Eventos Com Erro":
				mapStatus.put("EV", "Erro de Validação");
				mapStatus.put("EX","Erro na geração de XML");
				mapStatus.put("EA","Erro na Assinatura");
				mapStatus.put("EL","Erro na Geração do Lote");
			    break;
			case "Envio - Aguardando Envio":
				mapStatus.put("AE","Aguardando Envio");
				break;
			case "Envio - Eventos Enviados":
				mapStatus.put("AR","Enviado");
				break;				
			case "Eventos - Eventos Com Erro": 
				mapStatus.put("EE","Erro no Envio");
				mapStatus.put("RE","Retornado com Erro");
				break;		
			case "Eventos - Eventos Finalizados":
				mapStatus.put("FN","EFinalizado");
				break;	   	
		}
		
		return mapStatus;
		
		
		
	}
	
	
	
	
}
