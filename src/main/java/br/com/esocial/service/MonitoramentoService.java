package br.com.esocial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.esocial.estrutural.util.MascaraUtil;
import br.com.esocial.form.MonitoramentoEventoDTO;
import br.com.esocial.repository.MonitoramentoDAO;

@Service
public class MonitoramentoService {

	@Autowired
	private MonitoramentoDAO monitoramentoDAO;
	
	public List<MonitoramentoEventoDTO> pesquisarEventosPor(String origem, String periodo, String tipoEvento, String evento){
		List<MonitoramentoEventoDTO> listaEventos = monitoramentoDAO.pesquisarEventosPor(origem, periodo, tipoEvento, evento);
		List<MonitoramentoEventoDTO> listaMonitoramentoEventos = new ArrayList<MonitoramentoEventoDTO>();
		for(MonitoramentoEventoDTO eventoVO : listaEventos) {
			MonitoramentoEventoDTO monitoramentoDTO = monitoramentoDAO.pesquisarMonitoramento(eventoVO.getNomTabela(), periodo);
			monitoramentoDTO.setIdOrigem(eventoVO.getIdOrigem());
			monitoramentoDTO.setIdPeriodo(eventoVO.getIdPeriodo());
			monitoramentoDTO.setIdEvento(eventoVO.getIdEvento());
			monitoramentoDTO.setDescEvento(eventoVO.getDescEvento());
			monitoramentoDTO.setCodEvento(eventoVO.getCodEvento());
			monitoramentoDTO.setNomTabela(eventoVO.getNomTabela());
			monitoramentoDTO.setTipEvento(eventoVO.getTipEvento());
			monitoramentoDTO.setDescOrigem(eventoVO.getDescOrigem());
			monitoramentoDTO.setDescPeriodo(eventoVO.getDescPeriodo());
			preencherValoresEnvioPreEnvio(monitoramentoDTO);
			listaMonitoramentoEventos.add(monitoramentoDTO);
		}
		return listaMonitoramentoEventos;
	}
	
	public void preencherValoresEnvioPreEnvio(MonitoramentoEventoDTO dto) {
		dto.setPreEnvioEventosGerados(obterTotal(dto.getAguardandoGeracaoXml()));
		dto.setPreEnvioEventosValidados(obterTotal(dto.getAguardandoAssinatura(), dto.getAguardandoGeracaoLote()));
		dto.setPreEnvioEventosComErro(obterTotal(dto.getErroValidacao(),dto.getErroGeracaoXml(),dto.getErroAssinatura(),dto.getErroGeracaoLote()));
		dto.setEnvioAguardandoEnvio(obterTotal(dto.getAguardandoEnvio()));
		
		dto.setEnvioEventosEnviados(obterTotal(dto.getEnviado()));
		dto.setEnvioEventosComErro(obterTotal(dto.getErroNoEnvio(),dto.getRetornadoComErro()));
		dto.setEnvioEventosFinalizados(obterTotal(dto.getFinalizado()));
	}
	
	private String obterTotal(String... valores) {
		int total = 0;
		for(String valor : valores) {
			if(!valor.isEmpty()) {
				total += Integer.parseInt(valor);
			}
		}
		
        return MascaraUtil.formatarNumeroComPontoTresCasas(total);
	}
}