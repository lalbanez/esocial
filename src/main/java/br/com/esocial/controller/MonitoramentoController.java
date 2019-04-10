package br.com.esocial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.esocial.entity.par.ParCodigoVO;
import br.com.esocial.entity.par.ParOrigemVO;
import br.com.esocial.form.MonitoramentoEventoDTO;
import br.com.esocial.form.MonitoramentoForm;
import br.com.esocial.repository.ctr.CtrPeriodoRepository;
import br.com.esocial.repository.par.ParCodigoRepository;
import br.com.esocial.repository.par.ParEventoRepository;
import br.com.esocial.repository.par.ParOrigemRepository;
import br.com.esocial.service.MonitoramentoService;

@Controller
@RequestMapping("/monitoramento")
public class MonitoramentoController {

	private final String TELA_MONITORAMENTO = "pages/monitoramento/monitoramento";
	
	private final String COMBO_PERIODOS = "listaPeriodos";
	private final String COMBO_EVENTOS = "listaEventos";
	private final String REQUEST_LISTA_TABELAS = "listaTabelas";
	private final String REQUEST_LISTA_NAO_PERIODICOS = "listaNaoPeriodicos";
	private final String REQUEST_LISTA_PERIODICOS = "listaPeriodicos";
	
	@Autowired private ParOrigemRepository origemDAO;
	@Autowired private CtrPeriodoRepository periodoDAO;
	@Autowired private ParCodigoRepository codigoDAO;
	@Autowired private ParEventoRepository eventoDAO;
	@Autowired private MonitoramentoService monitoramentoService;
	
	
	
	@GetMapping("")
	public String iniciar() {
		return TELA_MONITORAMENTO;
	}

	@ModelAttribute("monitoramentoForm")
	public MonitoramentoForm popularForm() {   
		return new MonitoramentoForm(); 
	}
	
	/**___________________________________________________________________________________________________________________________________________________
	|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|_|
	|_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|
	|_____________________________________________________________________________________________________________________________________________________|
	|__|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__|
	|____|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__|*/
	
	@PostMapping("/carregarCombos")
	public String carregarCombos(Model mv, MonitoramentoForm form) {
		if(!form.getOrigem().isEmpty()) {
			mv.addAttribute(COMBO_PERIODOS, periodoDAO.pesquisarPeriodosEmAbertoPorOrigem(form.getOrigem()));
		}
		if(!form.getTipoEvento().isEmpty()) {
			mv.addAttribute(COMBO_EVENTOS, eventoDAO.findAllByTipEvento(form.getTipoEvento()));
		}
		return TELA_MONITORAMENTO;
	}
	
	@PostMapping("/pesquisar")
	public String pesquisar(Model mv, @Valid MonitoramentoForm form, BindingResult bindingResult) {
		carregarCombos(mv, form);
		
        if (bindingResult.hasErrors()) {
            return TELA_MONITORAMENTO;
        }
        
        boolean isRegistroEncontrado = false;
		if(form.getTipoEvento().isEmpty() || form.getTipoEvento().equals("1")) {
			List<MonitoramentoEventoDTO> listaMonitoramento =  monitoramentoService.pesquisarEventosPor(form.getOrigem(),form.getPeriodo(),"1", form.getEvento());
			if(!listaMonitoramento.isEmpty()) {
				mv.addAttribute(REQUEST_LISTA_TABELAS, listaMonitoramento);
				isRegistroEncontrado = true;
			}
		}
		
		if(form.getTipoEvento().isEmpty() || form.getTipoEvento().equals("2")) {
			List<MonitoramentoEventoDTO> listaMonitoramento =  monitoramentoService.pesquisarEventosPor(form.getOrigem(),form.getPeriodo(),"2", form.getEvento());
			if(!listaMonitoramento.isEmpty()) {
				mv.addAttribute(REQUEST_LISTA_NAO_PERIODICOS, listaMonitoramento);
				isRegistroEncontrado = true;
			}
		}
		if(form.getTipoEvento().isEmpty() || form.getTipoEvento().equals("3")) {
			List<MonitoramentoEventoDTO> listaMonitoramento =  monitoramentoService.pesquisarEventosPor(form.getOrigem(),form.getPeriodo(),"3", form.getEvento());
			if(!listaMonitoramento.isEmpty()) {
				mv.addAttribute(REQUEST_LISTA_PERIODICOS, listaMonitoramento);
				isRegistroEncontrado = true;
			}
		}
		
		if(isRegistroEncontrado) {
			mv.addAttribute("pesquisaRealizada", "registroEncontrado");
		}else {
			mv.addAttribute("pesquisaRealizada", "registroNaoEncontrado");
		}
		return TELA_MONITORAMENTO;
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
	
	@ModelAttribute("listaTiposEvento")
    public List<ParCodigoVO> popularTiposEvento(){
    	return codigoDAO.findAllByCodNum(1003);
    }
	
//	@ModelAttribute("listaEmpregadores")
//	public List<EmpregadorVO> popularEmpregadores(){
//		return empregadorDAO.findAll();
//	}
}