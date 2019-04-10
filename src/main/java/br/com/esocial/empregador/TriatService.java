package br.com.esocial.empregador;

import java.text.ParseException;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.esocial.estrutural.util.DataUtil;
import br.com.esocial.repository.ctr.CtrPeriodoDetRepository;

@Service
public class TriatService {
	
	@Autowired private TriatCadRepository triatCadDAO;
	@Autowired private TriatHCadRepository triatHCadDAO;
	@Autowired private TriatEventoRepository triatEventoDAO;
	@Autowired private TriatEventoRepository2 triatEventoDAO2;
	
	@Autowired private CtrPeriodoDetRepository ctrPeriodoDetDAO;
	
	public boolean isDataMenorQueDataImplantacaoDoSistemaEsocial(String YYYYMM) throws ParseException {
	  	return DataUtil.isPrimeiraDataMenorQueSegundaData("01/" + YYYYMM.substring(5, 7) + "/" + YYYYMM.substring(0, 4),"01/01/2010");
	}
	
	@Transactional
	public void gravar(TriatDTO triatDTO) throws ParseException {
		
		
		//Criar histórico (Jogar tudo da CAD para a HCAD)
		TriatHCadVO triatHist = new TriatHCadVO();
		TriatCadVO triatCadVO = triatCadDAO.findOne(triatDTO.getIdEmpregador());
		BeanUtils.copyProperties(triatCadVO, triatHist);
		triatHCadDAO.save(triatHist);
		
		
		//Atualizar o Cad atual com os registros da tela.( ou deve excluir e inserir novamente) ?
		BeanUtils.copyProperties(triatDTO, triatCadVO);
		triatCadVO.setDatUltAtu(Calendar.getInstance());
		triatCadVO.setNomUsuUltAtu("ESOCIAL");
		triatCadVO.setNomProUltAtu("Empregador Esocial");
		triatCadDAO.save(triatCadVO);
		
		
		//Atualizar o evento existente hoje para C.
		triatEventoDAO.updateAllFlgVigencia('C');
		
		//Em seguida criar o novo registro com flg_vigencia ativa.
		
		TriatEventoVO2 triatEventoVO = new ModelMapper().map(triatDTO, TriatEventoVO2.class);
		//TODO Achar alguma maneira eficiente de utilizar reflection para objetos dentro do objeto.
		triatEventoVO.setId("ID190412130001362018050311334000001"); // o campo está refletindo o id empregador.
		triatEventoVO.setCodIns(1);
		triatEventoVO.setIdEvento(6);//Evento Empregador
		triatEventoVO.setIdOrigem(1);
//		triatEventoVO.setIdPeriodoDet(ctrPeriodoDetDAO.findByIdEventoAndFlgStatus(6, 'A').getPeriodoVO().getIdPeriodo()); // nao funciona
		triatEventoVO.setIdPeriodoDet(12); // pegar o maior evento do 6 aberto
		
		triatEventoVO.setFlgVigencia('A');
		triatEventoVO.setDatIng(Calendar.getInstance());
		triatEventoVO.setDatUltAtu(Calendar.getInstance());
		triatEventoVO.setNomUsuUltAtu("ESOCIAL");
		triatEventoVO.setNomProUltAtu("Empregador Esocial");
		triatEventoVO.setCtrFlgStatus("AX");
		triatEventoDAO2.save(triatEventoVO);
		
    	//criar o evento
//		TriatEventoVO eventoVO = new TriatEventoVO();
		
		
//		-- AO TENTAR INCLUIR, OU ALTERAR, EXCLUIR
//		JOGAR O REGISTRO PRA HCAD, AUMENTAR + 1  NO SEQ_CAD_EMPREGADOR E CRIAR O REGISTRO NA CAD  ( SOH 1 REGISTRO).
//
//
//		EM SEGUIDA CRIAR UM REGISTRO NA TSOC_1000  (ctr_flg_acao = a )
//
//
//		tsoc_1000_empregador
//		flg_vigencia - c cancelado ou a ativo
//		seq_evento

	}
	
	
	
	
	
}
