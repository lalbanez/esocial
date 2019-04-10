package br.com.esocial.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.esocial.entity.ctr.CtrPeriodoVO;
import br.com.esocial.entity.par.ParEventoVO;
import br.com.esocial.entity.par.ParOrigemVO;
import br.com.esocial.estrutural.util.DataUtil;
import br.com.esocial.estrutural.util.MascaraUtil;
import br.com.esocial.estrutural.util.StringUtil;
import br.com.esocial.form.MonitoramentoConsultaDTO;
import br.com.esocial.form.MonitoramentoConsultaForm;
import br.com.esocial.form.MonitoramentoEventoDTO;
import br.com.esocial.repository.ctr.CtrPeriodoRepository;
import br.com.esocial.repository.par.ParEventoRepository;
import br.com.esocial.repository.par.ParOrigemRepository;
import br.com.esocial.service.MonitoramentoConsultaService;

@Controller
@RequestMapping("/monitoramentoConsulta")
public class MonitoramentoConsultaController {
	
	private final String TELA_MONITORAMENTO_CONSULTA = "pages/monitoramento/monitoramentoConsulta";
	
	@Autowired private ParOrigemRepository origemDAO;
	@Autowired private CtrPeriodoRepository periodoDAO;
	@Autowired private ParEventoRepository eventoDAO;
	@Autowired private MonitoramentoConsultaService monitoramentoConsultaService;
	
	@GetMapping("")
	public String iniciar(Model mv, MonitoramentoConsultaForm form) { 
		return TELA_MONITORAMENTO_CONSULTA;	
	}
	
	@ModelAttribute("monitoramentoConsultaForm")
	public MonitoramentoConsultaForm popularForm() { 
		return new MonitoramentoConsultaForm();
	}
	
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new MonitoramentoConsultaFormValidator());
    }
    
//    @Autowired
//    @Qualifier("monitoramentoConsultaFormValidator")
//    private Validator validator;

	/**___________________________________________________________________________________________________________________________________________________
	|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|_|
	|_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|
	|_____________________________________________________________________________________________________________________________________________________|
	|__|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__|
	|____|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__|*/
	
	@PostMapping("/pesquisar")
	public String pesquisar(Model mv, @Valid MonitoramentoConsultaForm form, BindingResult bindingResult) {
		
	        if (bindingResult.hasErrors()) {
	        	
	        	return TELA_MONITORAMENTO_CONSULTA;
	        }
	        
		List<MonitoramentoConsultaDTO> listaDetalhes =  monitoramentoConsultaService.pesquisarDetalhes(form.getOrigem(), form.getPeriodo(), form.getEvento(),
                form.getIdEvento(), form.getNumLote(), form.getDataEnvio(), form.getProtocoloEnvio(), form.getDataRetorno(), form.getNumRecibo(),
                form.getDataGeracao(), form.getNumCpf(), form.getDescStatus(), form.getAgrupacaoStatus());
		
		mv.addAttribute("listaDetalhes", listaDetalhes);
		
		
		if(!listaDetalhes.isEmpty()) {
			mv.addAttribute("pesquisaRealizada", "registroEncontrado");
		}else {
			mv.addAttribute("pesquisaRealizada", "registroNaoEncontrado");
		}
					
		return TELA_MONITORAMENTO_CONSULTA;
	}
	
	
	@ModelAttribute("listaStatusEvento")
	public List<MonitoramentoEventoDTO> popularListaEventosStatus(Model mv, MonitoramentoConsultaForm form){
		 List<MonitoramentoEventoDTO> listaEventosStatus =  monitoramentoConsultaService.pesquisarEventoListaPor(form.getOrigem(), form.getPeriodo(), form.getTipoEvento(), form.getEvento(), form.getAgrupacaoStatus());
		Integer totalStatus = 0;
		for(MonitoramentoEventoDTO eventoDTO : listaEventosStatus) {
			if(!eventoDTO.getTotalStatusEvento().isEmpty()) {
				 totalStatus += Integer.parseInt(eventoDTO.getTotalStatusEvento().replaceAll("[.]", ""));
			}
		}
		
		mv.addAttribute("totalStatus", MascaraUtil.formatarNumeroComPontoTresCasas(totalStatus));
		
		return listaEventosStatus;
	}
	
	/**___________________________________________________________________________________________________________________________________________________
	|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|_|
	|_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|
	|_____________________________________________________________________________________________________________________________________________________|
	|__|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__|
	|____|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__|*/
	
	@ModelAttribute("listaOrigens")
	public List<ParOrigemVO> popularOrigens() {
       return origemDAO.findAll();
	}
	
	@ModelAttribute("listaPeriodos")
	public List<CtrPeriodoVO> popularPeriodos(MonitoramentoConsultaForm form){
		return  periodoDAO.pesquisarPeriodosEmAbertoPorOrigem(form.getOrigem());
	}
	
	@ModelAttribute("listaEventos")
	public List<ParEventoVO> popularEventos(MonitoramentoConsultaForm form){
		return eventoDAO.findAllByTipEvento(form.getTipoEvento());
	}
	
	@ModelAttribute("listaStatus")
	public LinkedHashMap<String, String> popularStatus(MonitoramentoConsultaForm form){
		return monitoramentoConsultaService.obterListaStatusPor(form.getAgrupacaoStatus());
	}
	
	
//	https://www.logicbig.com/tutorials/spring-framework/spring-core/creating-custom-validation-constraints.html
	public class MonitoramentoConsultaFormValidator implements Validator {

		   @Override
		   public boolean supports (Class<?> clazz) {
		       return MonitoramentoConsultaForm.class == clazz;
		   }

		   @Override
		   public void validate (Object target, Errors errors) {
			   
			   MonitoramentoConsultaForm form = (MonitoramentoConsultaForm) target;
			   
			   if(!form.getDataEnvio().isEmpty()) {
				   if(!DataUtil.isDataDDMMYYYYValida(form.getDataEnvio())){
					   errors.rejectValue("dataEnvio", "","* A Data de Envio está inválida.");
				   }
			   }
			   
			   if(!form.getDataRetorno().isEmpty()) {
				   if(!DataUtil.isDataDDMMYYYYValida(form.getDataRetorno())){
					   errors.rejectValue("dataRetorno", "","* A Data de Retorno está inválida.");
				   }
			   }  
			   
			   if(!form.getDataGeracao().isEmpty()) {
				   if(!DataUtil.isDataDDMMYYYYValida(form.getDataGeracao())){
					   errors.rejectValue("dataGeracao", "","* A Data de Geração está inválida.");
				   }
			   }

			   if(!form.getNumCpf().isEmpty()) {
				   if(!StringUtil.isCPF(form.getNumCpf())) {
					   errors.rejectValue("numCpf", "","* O campo CPF está inválido.");
				   }
			   }
			   
			   //Terceira Validação
			   if(!errors.hasErrors()) {
				   try {
					   if(!form.getDataEnvio().isEmpty() && !form.getDataRetorno().isEmpty()) {
						
								Calendar dataEnvio = DataUtil.obterCalendarSemHorario(form.getDataEnvio());
								Calendar dataRetorno = DataUtil.obterCalendarSemHorario(form.getDataRetorno());
								
								if(DataUtil.isPrimeiraDataMaiorQueSegundaData(dataEnvio, dataRetorno)) {
									errors.rejectValue("dataRetorno", "","* A Data de Retorno não pode ser menor que a Data de Envio.");
								}
	
						 }
					} catch (ParseException e) {
						e.printStackTrace();
					}
			   }
//		       ValidationUtils.rejectIfEmpty(errors, "dataEnvio", "date.empty","aew");
//		       }
		   }
		}
	
}
