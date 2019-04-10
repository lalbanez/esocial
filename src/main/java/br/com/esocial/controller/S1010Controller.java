package br.com.esocial.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.esocial.empregador.TriatEventoRepository;
import br.com.esocial.empregador.TriatEventoVO;
import br.com.esocial.entity.par.ParCodigoVO;
import br.com.esocial.entity.par.ParSigePrevEsocialVO;
import br.com.esocial.estrutural.util.DataUtil;
import br.com.esocial.estrutural.util.MascaraUtil;
import br.com.esocial.form.S1010Form;
import br.com.esocial.repository.par.ParCodigoRepository;
import br.com.esocial.repository.par.ParSigePrevEsocialRepository;
import br.com.esocial.rubricas.Ambiente;
import br.com.esocial.rubricas.AmbienteRepository;
import br.com.esocial.rubricas.ProcessoCadRubrica;
import br.com.esocial.rubricas.ProcessoCadRubricaRepository;
import br.com.esocial.rubricas.Rubrica;
import br.com.esocial.rubricas.RubricaRepository;
import br.com.esocial.rubricas.S1010;
import br.com.esocial.rubricas.S1010Cad;
import br.com.esocial.rubricas.S1010CadRepository;
import br.com.esocial.rubricas.S1010HCad;
import br.com.esocial.rubricas.S1010HCadRepository;
import br.com.esocial.rubricas.S1010Repository;

@Controller
@RequestMapping("/s1010")
public class S1010Controller {

	private final String TELA_S_1010 = "pages/rubricas/s1010";
//	private final String RUBRICA_TESTE = "1860250"; 
//	private final Integer RUBRICA_TESTE = 7005756;
	
	@Autowired private ParCodigoRepository codigoDAO;
	
	@Autowired private TriatEventoRepository empregadorRepository;
	@Autowired private S1010Repository s1010DAO;
	@Autowired private S1010CadRepository s1010CadDAO;
	@Autowired private S1010HCadRepository s1010HCadDAO;
	@Autowired private RubricaRepository rubricaDAO;
//	@Autowired private RubricaIRRepository rubricaIrDAO;
//	@Autowired private RelRubricaRepository relRubricaDAO;
//	@Autowired private FormulaCalculoRepository formulaCalculoDAO;
	@Autowired private AmbienteRepository ambienteDAO;
	@Autowired private ParSigePrevEsocialRepository parSigePrevEsocialDAO;
	@Autowired private ProcessoCadRubricaRepository processoCadRubricaDAO;
//	@Autowired private ProcessoHCadRubricaRepository processoHCadRubricaDAO;

	
	List<ProcessoCadRubrica> processo;

	@InitBinder("s1010Form")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new S1010Validator());
    }
	
	@ModelAttribute("s1010Form")
	public S1010Form popularForm() {
		return new S1010Form();
	}
	
	
	@RequestMapping("")
	public String iniciar(Model mv,S1010Form form) throws ParseException  {
		dadosFixos(form);
		Integer	idConsulta = Integer.parseInt(form.getIdRubricaConsulta());	
		
		if(idConsulta != null && !idConsulta.equals(0)) {
			processo = new ArrayList<ProcessoCadRubrica>();
		
			S1010Cad s1010cad = s1010CadDAO.findFirstByCodRubricaOrderBySeqCadDesc(idConsulta);
			s1010cad.setSeqCad(s1010cad.getSeqCad()+1);
			s1010cad.setCodIns(1);
	
			form.setCodRubrica(String.valueOf(s1010cad.getCodRubrica()));
			form.setIdRubrica(s1010cad.getNomRubrica());
			form.setNatureza(s1010cad.getFlgNatureza());
			form.setIniVigenciaOperacao(DataUtil.obterStringAnoMes(s1010cad.getDatIniVig()));
			form.setFimVigenciaOperacao(DataUtil.obterStringAnoMes(s1010cad.getDatFimVig()));
			form.setTipoRubrica(String.valueOf(s1010cad.getCodTipRubEsocial()));
			form.setIncidenciaPrevSocial(MascaraUtil.formataNumero(String.valueOf(s1010cad.getCodIncCpEsocial()), "00", MascaraUtil.BRASIL));
			form.setIncidenciaFGTS(MascaraUtil.formataNumero(String.valueOf(s1010cad.getCodIncFgtsEsocial()),"00", MascaraUtil.BRASIL));
			form.setIncidenciaIRRF(MascaraUtil.formataNumero(String.valueOf(s1010cad.getCodIncIrrfEsocial()), "00", MascaraUtil.BRASIL));
			form.setIncidenciaContribSindical(MascaraUtil.formataNumero(String.valueOf(s1010cad.getCodIncSindEsocial()), "00", MascaraUtil.BRASIL));
			form.setIncidenciaContribRPPS(MascaraUtil.formataNumero(String.valueOf(s1010cad.getCodIncCprpEsocial()), "00", MascaraUtil.BRASIL));
			form.setTetoRemuneratorio(s1010cad.getFlgTetoRemEsocial());
			form.setObservacao(s1010cad.getDesObsRub());
			/********************************************************************************************/
			
			/*************************************Dados Processo***********************************/
			
			List<ProcessoCadRubrica> listaProcesso = processoCadRubricaDAO.findAllByIdCadRub(s1010cad.getIdCadRub());
			if(!listaProcesso.isEmpty()) {
				form.setListaProcesso(listaProcesso);	
			}
			
			processo.addAll(form.getListaProcesso());
			mv.addAttribute("listaProcesso", processo);
//			mv.addAttribute("listaProcesso", form.getListaProcesso());
			/*************************************Dados Processo***********************************/
			mv.addAttribute("s1010Form", form);
		}
		return TELA_S_1010;
	}

	@ModelAttribute("carregarDados")
	public void carregarDados (S1010Form form, Model mv) throws ParseException {
		dadosFixos(form);
	}
	
	
	@PostMapping("/gravar")
	public String gravar(Model mv, @Valid S1010Form form, BindingResult bindingResult) throws ParseException {
		mv.addAttribute("listaProcesso", processo);
		if(bindingResult.hasErrors()) {
			return TELA_S_1010;
		}
		
		Integer idConsulta = Integer.parseInt(form.getCodRubrica());

		S1010Cad s1010cadConsulta = s1010CadDAO.findFirstByCodRubricaOrderBySeqCadDesc(idConsulta);
		S1010HCad s1010hCadConsulta = s1010HCadDAO.findFirstByCodRubricaOrderBySeqHistDesc(idConsulta);
		Rubrica rubricas = rubricaDAO.findFirstByCodRubricaOrderByCodEntidade(idConsulta);
//		RubricaIR rubricaIR = rubricaIrDAO.findFirstByCodRubrica(idRubrica);
//		List<RelRubricas> relRubricas = relRubricaDAO.findCodAgrupacaoRubricaByCodRubrica(idRubrica);
//		FormulaCalculo formulaCalculo = formulaCalculoDAO.findFirstIndGravaDetalheByCodRubrica(idRubrica);
		
//		S1010Cad s1010cadGravacao = new S1010Cad();
		S1010HCad s1010hCadGravacao = new S1010HCad();
//		ProcessoCadRubrica processoCadRubrica = new ProcessoCadRubrica();

		//******************************************************* GRAVACAO DE HISTORICO ******************************************//
		if(s1010hCadConsulta != null && !s1010hCadConsulta.getSeqHist().equals(0)) {
			s1010hCadGravacao.setSeqHist(s1010hCadConsulta.getSeqHist()+1);
		} else {
			s1010hCadGravacao.setSeqHist(1);
		}
		
		s1010hCadGravacao.setCodIns(s1010cadConsulta.getCodIns());
		s1010hCadGravacao.setCodRubrica(s1010cadConsulta.getCodRubrica());
		s1010hCadGravacao.setTabRubrica(s1010cadConsulta.getTabRubrica());
		s1010hCadGravacao.setDatIniVig(s1010cadConsulta.getDatIniVig());
		s1010hCadGravacao.setDatFimVig(s1010cadConsulta.getDatFimVig());
		s1010hCadGravacao.setNomRubrica(s1010cadConsulta.getNomRubrica());
		s1010hCadGravacao.setFlgNatureza(s1010cadConsulta.getFlgNatureza());
		s1010hCadGravacao.setTipEventoEspecial(s1010cadConsulta.getTipEventoEspecial());
		s1010hCadGravacao.setCodEntidade(s1010cadConsulta.getCodEntidade());
		s1010hCadGravacao.setFlgDedIr(s1010cadConsulta.getFlgDedIr());
		s1010hCadGravacao.setFlgBaseIr(s1010cadConsulta.getFlgBaseIr());
		s1010hCadGravacao.setIndGravaDetalhe(s1010cadConsulta.getIndGravaDetalhe());
		s1010hCadGravacao.setDatUltAtu(Calendar.getInstance());
		s1010hCadGravacao.setDatIng(Calendar.getInstance());
		s1010hCadGravacao.setNomUsuUltAtu("LALBANEZ"); // Incluir Usuario Logado
		s1010hCadGravacao.setNomProUltAtu(S1010Controller.class.toString()); 
		s1010hCadGravacao.setIdCadRub(s1010cadConsulta.getIdCadRub());
		s1010hCadGravacao.setFlgPossuiGrupo9(s1010cadConsulta.getFlgPossuiGrupo9());
		s1010hCadGravacao.setFlgPossuiGrupoN9(s1010cadConsulta.getFlgPossuiGrupoN9());
		s1010hCadGravacao.setFlgIncContribRppsMil(s1010cadConsulta.getFlgIncContribRppsMil());
		
		s1010hCadGravacao.setCodNatRubEsocial(Integer.parseInt(s1010cadConsulta.getFlgNatureza()));
		s1010hCadGravacao.setCodTipRubEsocial(s1010cadConsulta.getCodRubrica());
		s1010hCadGravacao.setCodIncCpEsocial(s1010cadConsulta.getCodIncCpEsocial());
		s1010hCadGravacao.setCodIncIrrfEsocial(s1010cadConsulta.getCodIncIrrfEsocial());
		s1010hCadGravacao.setCodIncFgtsEsocial(s1010cadConsulta.getCodIncFgtsEsocial());
		s1010hCadGravacao.setCodIncSindEsocial(s1010cadConsulta.getCodIncSindEsocial());
		s1010hCadGravacao.setCodIncCprpEsocial(s1010cadConsulta.getCodIncCprpEsocial());
		s1010hCadGravacao.setFlgTetoRemEsocial(form.getTetoRemuneratorio());
		s1010hCadGravacao.setDesObsRub(form.getObservacao());
		s1010hCadGravacao.setFlgInput("N");
		

//		for (ProcessoCadRubrica processo : s1010cadConsulta.getListaProcessos()) {
//			ProcessoHCadRubrica processoHistorico = new ProcessoHCadRubrica();
//			processoHistorico.setIdProcRub(processo.getIdProcRub());
//			processoHistorico.setIdCadRub(processo.getIdCadRub());
//			processoHistorico.setTpproc(processo.getTpproc());
//			processoHistorico.setNrproc(processo.getNrproc());
//			processoHistorico.setExtdecisao(processo.getExtdecisao());
//			processoHistorico.setCodsusp(processo.getCodsusp());
//			processoHistorico.setDatIng(processo.getDatIng());
//			processoHistorico.setDatUltAtu(processo.getDatIng());
//			processoHistorico.setNomProUltAtu(processo.getNomProUltAtu());
//			processoHistorico.setNomUsuUltAtu(processo.getNomUsuUltAtu());
//			processoHistorico.setTipProcesso(processo.getTipProcesso());
//			// processoHistorico.setIdHistProcRub(processo.);
//			if (s1010hCadConsulta != null && !s1010hCadConsulta.getSeqHist().equals(0)) {
//				processoHistorico.setSeqHist(s1010hCadGravacao.getSeqHist() + 1);
//			} else {
//				processoHistorico.setSeqHist(1);
//			}
//			processoHistorico.setFlgInput(processo.getFlgInput());
//		}
		
		s1010HCadDAO.save(s1010hCadGravacao);
		
		//******************************************************* GRAVACAO DE HISTORICO ******************************************//
		
		//******************************************************* GRAVACAO DO CADASTRO ******************************************//
		
		if(s1010cadConsulta != null && !s1010cadConsulta.getSeqCad().equals(0)) {
			s1010cadConsulta.setSeqCad(s1010cadConsulta.getSeqCad()+1);
		} else {
			s1010cadConsulta.setSeqCad(1);
		}
		s1010cadConsulta.setCodIns(1);
		s1010cadConsulta.setCodRubrica(Integer.parseInt(form.getCodRubrica()));
		s1010cadConsulta.setTabRubrica("TB_RUBRI");
		s1010cadConsulta.setDatIniVig(DataUtil.obterCalendarAnoMes(form.getIniVigenciaOperacao()));
		s1010cadConsulta.setDatFimVig(DataUtil.obterCalendarAnoMes(form.getFimVigenciaOperacao()));
		s1010cadConsulta.setNomRubrica(form.getIdRubrica());
		s1010cadConsulta.setFlgNatureza(form.getNatureza());
		s1010cadConsulta.setTipEventoEspecial(rubricas.getTipEventoEspecial()); 
		s1010cadConsulta.setCodEntidade(rubricas.getCodEntidade());
//		s1010cadConsulta.setFlgDedIr(rubricaIR.getFlgDedIr()); 
//		s1010cadConsulta.setFlgBaseIr(rubricaIR.getFlgBaseIr()); 
//		s1010cadConsulta.setIndGravaDetalhe(formulaCalculo.getIndGravaDetalhe());
		s1010cadConsulta.setDatIng(Calendar.getInstance());
		s1010cadConsulta.setDatUltAtu(Calendar.getInstance());
		s1010cadConsulta.setNomUsuUltAtu("LALBANEZ"); //TODO VERIFICAR COMO CAPTURAR USUARIO LOGADO
		s1010cadConsulta.setNomProUltAtu("S1010Controller");

//		for(RelRubricas relRubrica : relRubricas) {
//			if(relRubrica.getCodAgrupacaoRubrica().equals(9)) {
//				s1010cadConsulta.setFlgPossuiGrupo9("S");
//				s1010cadConsulta.setFlgPossuiGrupoN9("N");
//				break;
//			} else {
//				s1010cadConsulta.setFlgPossuiGrupo9("N");
//				s1010cadConsulta.setFlgPossuiGrupoN9("S");
//			}
//		}

		s1010cadConsulta.setCodNatRubEsocial(Integer.parseInt(form.getNatureza()));
		s1010cadConsulta.setCodTipRubEsocial(Integer.parseInt(form.getCodRubrica()));
		s1010cadConsulta.setCodIncCpEsocial(Integer.parseInt(form.getIncidenciaPrevSocial()));
		s1010cadConsulta.setCodIncIrrfEsocial(Integer.parseInt(form.getIncidenciaIRRF()));
		s1010cadConsulta.setCodIncFgtsEsocial(Integer.parseInt(form.getIncidenciaFGTS()));
		s1010cadConsulta.setCodIncSindEsocial(Integer.parseInt(form.getIncidenciaContribSindical()));
		s1010cadConsulta.setCodIncCprpEsocial(Integer.parseInt(form.getIncidenciaContribRPPS()));
		s1010cadConsulta.setFlgTetoRemEsocial(form.getTetoRemuneratorio());
		s1010cadConsulta.setDesObsRub(form.getObservacao());
		s1010cadConsulta.setFlgInput("N");
		
		/************************ Gravação Dados Processo *****************************/

		for (ProcessoCadRubrica processoCad : processo) {
			if(processoCad.getIdProcRub() != null){
				continue;
			}
			processoCad.setIdCadRub(s1010cadConsulta.getIdCadRub());
			processoCad.setDatIng(Calendar.getInstance());
			processoCad.setDatUltAtu(Calendar.getInstance());
			processoCad.setNomUsuUltAtu("LALBANEZ");
			processoCad.setNomProUltAtu("S1010Controller");
			processoCad.setFlgInput("N");
			s1010cadConsulta.getListaProcessos().add(processoCad);
		}
		/******************************************************************************/
		
		s1010CadDAO.save(s1010cadConsulta);

		
		
		//******************************************************* GRAVACAO DO CADASTRO ******************************************//
		
		return TELA_S_1010;
	}
	
	
	
	
@RequestMapping(value="incluir", method=RequestMethod.POST)
public String incluirProcesso(Model mv,  
		@RequestParam(value="idProcesso") String idProcesso,
		@RequestParam(value="tpProcesso") String tpProcesso,
		@RequestParam(value="numProcesso") String numProcesso,
		@RequestParam(value="extDecisao") String extDecisao,
		@RequestParam(value="codSuspensao") String codSuspensao) {
		ProcessoCadRubrica processoCadRubrica = new ProcessoCadRubrica();
		
		processoCadRubrica.setTipProcesso(Integer.parseInt(idProcesso));
		switch(Integer.parseInt(idProcesso)) {
		case 1:
			processoCadRubrica.setTpproc(Integer.parseInt(tpProcesso));
			processoCadRubrica.setNrproc(numProcesso);
			processoCadRubrica.setExtdecisao(Integer.parseInt(extDecisao));
			processoCadRubrica.setCodsusp(Integer.parseInt(codSuspensao));
			break;
		case 2:
			processoCadRubrica.setNrproc(numProcesso);
			processoCadRubrica.setCodsusp(Integer.parseInt(codSuspensao));
			break;
		case 3:
			processoCadRubrica.setNrproc(numProcesso);
			break;
		case 4:
			processoCadRubrica.setNrproc(numProcesso);
			break;
		case 5:
			processoCadRubrica.setTpproc(Integer.parseInt(tpProcesso));
			processoCadRubrica.setNrproc(numProcesso);
			processoCadRubrica.setExtdecisao(Integer.parseInt(extDecisao));
			break;
		}

		processo.add(processoCadRubrica);
		
		mv.addAttribute("listaProcesso", processo);
		
		return TELA_S_1010 + " :: processos";
	}
	
	
	
	@ModelAttribute("listaIdentificacaoAmbiente")
	public List<ParCodigoVO> getListaIdentificacaoAmbiente() {
	    return codigoDAO.findAllByCodNum(1005);
	}
	
	@ModelAttribute("listaProcessoEmissaoEvento")
	public List<ParCodigoVO> getListaProcessoEmissaoEvento() {
	    return codigoDAO.findAllByCodNum(1006);
	}
	
	@ModelAttribute("listaTipoAcao")
	public List<ParCodigoVO> getListaTipoAcao() {
	    return codigoDAO.findAllByCodNum(1001);
	}
	
	@ModelAttribute("listaTipoRubrica")
	public List<ParCodigoVO> getListaTipoRubrica() {
	    return codigoDAO.findAllByCodNum(1012);
	}
	
	@ModelAttribute("listaNatureza")
	public List<ParSigePrevEsocialVO> getListaNatureza() {
	    return parSigePrevEsocialDAO.findCodEsocialandDesEsocialDistinctByCodTipoOrderByCodEsocial(11);
	}
	
	@ModelAttribute("listaIncidenciaPrevSocial")
	public List<ParCodigoVO> getListaIncidenciaPrevSocial() {
	    return codigoDAO.findAllByIdDefinicao(21);
	}
	
	@ModelAttribute("listaIncidenciaIRRF")
	public List<ParCodigoVO> getListaIncidenciaIRRF() {
		return codigoDAO.findAllByIdDefinicao(20);
	}
	
	@ModelAttribute("listaIncidenciaFGTS")
	public List<ParCodigoVO> getlListaIncidenciaFGTS() {
		return codigoDAO.findAllByIdDefinicao(19);
	}
	
	@ModelAttribute("listaContribSindical")
	public List<ParCodigoVO> getListaIncidenciaContribSocial() {
		return codigoDAO.findAllByIdDefinicao(18);
	}
	
	
	@ModelAttribute("listaIncidenciaContribRPPS")
	public List<ParCodigoVO> getListaIncidenciaContribRPPS() {
		return codigoDAO.findAllByIdDefinicao(17);
	}
	
	@ModelAttribute("listaSimNao")
	public List<ParCodigoVO> getListaSimNao() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("S").desDescricao("Sim").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("N").desDescricao("Não").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaIdenficacaoProcesso")
	public List<ParCodigoVO> getListaIdetificacaoProceso() {
		return codigoDAO.findAllByCodNum(1013);
	}
		
	public void dadosFixos(S1010Form form) throws ParseException {
		/***********************Identificacao do Evento**********************/
		Ambiente ambiente = ambienteDAO.findOne(1);

		form.setIdentificacaoAmbiente(String.valueOf(ambiente.getTpamb()));
		form.setVersaoProcessoEmissaoEvento(ambiente.getVerproc());
		form.setProcessoEmissaoEvento(String.valueOf(ambiente.getProcemi()));
		/*********************************************************************/
		
		/***********************Identificação do Empregador**********************/
		TriatEventoVO empregador = empregadorRepository.findByFlgVigencia('A');
		
		form.setTipoInscricao(""+empregador.getTpinsc() + (empregador.getTpinsc().equals(1) ? " - CNPJ" : " - CPF"));
		form.setNumInscricao(empregador.getNrinsc());
		/*********************************************************************/

		/***************************Dados Rubrica*****************************/
		Integer idConsulta = 0;
		if(form.getIdRubricaConsulta() != null) {
			idConsulta = Integer.parseInt(form.getIdRubricaConsulta());
		} else if (form.getCodRubrica() != null) {
			idConsulta = Integer.parseInt(form.getCodRubrica());
		}
		
		if(!idConsulta.equals(0)) {
			S1010 s1010 = s1010DAO.findFirstByiderubricaCodrubrOrderBySeqEventoDesc(idConsulta);
	
			form.setIdEventoUltEvGerado(s1010.getId());
			form.setDtGeracaoUltEvGerado(DataUtil.obterStringAnoMes(s1010.getDat_ult_atu()));
			form.setTipoAcaoUltEvGerado(s1010.getCtr_flg_acao().getDescricao());
			form.setIniVigenciaUltEvGerado(s1010.getIderubricaInivalid());
			form.setFimVigenciaUltEvGerado(s1010.getIderubricaFimvalid());
			form.setStatusEventoUltEvGerado(s1010.getCtr_flg_status().getDescricao());
	//		form.setDataRetornoUltEvGerado();
			form.setNumLoteUltEvGerado(String.valueOf(s1010.getId_lote()));
			form.setNumProtocoloEnvioUltEvGerado(String.valueOf(s1010.getWs_cod_resposta()));
	//		form.setNumReciboUltEvGerado();
	//		form.setDataEnvioUltEvGerado();
	//		form.setDataRetornoUltEvGerado();
			form.setIdProcesso("");
		}
	}
	
	
	@ModelAttribute("dadosUltimoEventoGerado")
	public S1010 getDadosUltimoEventoGerado(S1010Form form) {
		Integer idConsulta = 0;
		if(form.getIdRubricaConsulta() != null) {
			idConsulta = Integer.parseInt(form.getIdRubricaConsulta());
		} else if (form.getCodRubrica() != null) {
			idConsulta = Integer.parseInt(form.getCodRubrica());
		}
		return s1010DAO.findFirstByiderubricaCodrubrOrderBySeqEventoDesc(idConsulta);
	}
	
	
	public class S1010Validator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return S1010Form.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			S1010Form form = (S1010Form) target;
			
			S1010 s1010 = s1010DAO.findFirstByiderubricaCodrubrOrderBySeqEventoDesc(Integer.parseInt(form.getCodRubrica()));

			//Segunda Validação
//			try {
//				Calendar iniUser = DataUtil.obterCalendarAnoMes(form.getIniVigenciaOperacao());
//				Calendar fimUser = DataUtil.obterCalendarAnoMes(form.getFimVigenciaOperacao());
//				Calendar iniRub = DataUtil.obterCalendarAnoMes(s1010.getIderubricaInivalid());
//				Calendar fimRub = DataUtil.obterCalendarAnoMes(s1010.getIderubricaFimvalid());
//				
//				if((iniUser.getInstance() != iniRub.getInstance()) && DataUtil.isPrimeiraDataSobrepostaSegundaData(iniUser, fimUser, iniRub, fimRub)) {
//					errors.rejectValue("iniVigenciaOperacao", "", "* Já existe uma rubrica dentro desse período de vigência.");
//				}
//				if((fimUser.getInstance() != fimRub.getInstance()) && DataUtil.isPrimeiraDataSobrepostaSegundaData(iniUser, fimUser, iniRub, fimRub)) {
//					errors.rejectValue("fimVigenciaOperacao", "", "* Já existe uma rubrica dentro desse período de vigência.");
//				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			
			

//			if (!form.getNumero2().isEmpty()) {
//				if (!form.getNumero2().matches("^(0|[1-9][0-9]*)$")) {
//					errors.rejectValue("numero2", "", "* O campo deve ser numérico.");
//				}
//			}
//			
//			//Terceira Validação
//			if(!errors.hasErrors()) {
//				if (form.getNumero1().length() > 5) {
//					errors.rejectValue("numero1", "", "* O campo deve conter até 5 números.");
//				}
//				
//				if (form.getNumero2().length() > 5) {
//					errors.rejectValue("numero2", "", "* O campo deve conter até 5 números.");
//				}
//			}
			
		}

	}
	
	
	public List<ProcessoCadRubrica> getProcesso() {
		return processo;
	}

	public void setProcesso(List<ProcessoCadRubrica> processo) {
		this.processo = processo;
	}
	
}
