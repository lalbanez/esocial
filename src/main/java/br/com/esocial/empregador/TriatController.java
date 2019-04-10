package br.com.esocial.empregador;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.esocial.entity.par.ParCodigoVO;
import br.com.esocial.estrutural.util.DataUtil;
import br.com.esocial.estrutural.util.GeraCpfCnpj;
import br.com.esocial.estrutural.util.StringUtil;
import br.com.esocial.repository.par.ParCodigoRepository;

@Controller
@RequestMapping("/triat")
public class TriatController {

	private final String TELA_EMPREGADOR = "pages/empregador/triat";
	private final String TELA_EMPREGADOR_2 = "pages/empregador/triat2";
	
	
	@Autowired private ParCodigoRepository codigoDAO;
	@Autowired private TriatCadRepository triatCadDAO;
	@Autowired private TriatEventoRepository triatEventoDAO;
	@Autowired private TriatService triatService;
	
	
	
	@ModelAttribute("triatForm")
	public TriatForm popularForm() throws ParseException {  
		return new TriatForm();
	}
	
    @InitBinder("triatForm")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new TriatFormValidator());
    }
    
	/**
	 * Início Dados do Último Evento gerado
	 * 
	 */
	@ModelAttribute("dadosUltimoEventoGerado")
	public TriatEventoVO getDadosUltimoEventoGerado() {
		return triatEventoDAO.findByFlgVigencia('A');
	}
	
    
	
	
	@RequestMapping("carregarPagina")
//	@Transactional
	public String carregarPagina(Model mv,TriatForm form) throws ParseException {
		
		TriatCadVO triatCadVO = triatCadDAO.findOne(Integer.parseInt(form.getIdEmpregador()));
		TriatDTO triatDTO  = new ModelMapper().map(triatCadVO, TriatDTO.class);
		TriatForm triatForm = TriatDTO.converterDTOparaForm(triatDTO);  
		
		 mv.addAttribute("triatForm", triatForm);
		return TELA_EMPREGADOR;
	}
	
	
	@PostMapping("carregarPaginaDetalhe")
//	@Transactional
	public String carregarPaginaDetalhe(Model mv,TriatForm form) throws ParseException {
		
//		TriatEventoVO triatEventoVO = triatEventoDAO.findOne(1);
//		
//		TriatCadVO triatCadVO = triatCadDAO.findOne(3);
//		TriatDTO triatDTO = new TriatDTO();
//		BeanUtils.copyProperties(triatCadVO, triatDTO);
//		TriatForm triatForm = TriatDTO.converterDTOparaForm(triatDTO);  
		
		TriatCadVO triatCadVO = triatCadDAO.findOne(Integer.parseInt(form.getIdEmpregador()));
		TriatDTO triatDTO  = new ModelMapper().map(triatCadVO, TriatDTO.class);
		TriatForm triatForm = TriatDTO.converterDTOparaForm(triatDTO);  
		
		 mv.addAttribute("triatForm", triatForm);
		 mv.addAttribute("eventoEmpregadorVO", getDadosUltimoEventoGerado());
		 
		return TELA_EMPREGADOR_2;
	}
	
	
	

	
	@PostMapping("/gravar")
	public String gravar(Model mv, @Valid TriatForm form, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
        	 mv.addAttribute("resultado", "erroFormulario");
        	return TELA_EMPREGADOR;
        }
        
       
       triatService.gravar(TriatDTO.converterFormParaDTO(form));
		
       mv.addAttribute("dadosUltimoEventoGerado", getDadosUltimoEventoGerado());
       mv.addAttribute("resultado", "OK");
	   return TELA_EMPREGADOR;
	}
	
	
	
	

	
	
	/**
	 * Início Cabeçalho
	 */
	@ModelAttribute("listaIdentificacaoAmbiente")
	public List<ParCodigoVO> getListaIdentificacaoAmbiente() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Produção").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("2").desDescricao("Produção Restrita").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaProcessoEmissaoEvento")
	public List<ParCodigoVO> getListaProcessoEmissaoEvento() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Aplicativo do empregador").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("2").desDescricao("Aplicativo governamental - Empregador Doméstico").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("3").desDescricao("Aplicativo governemental - Web Geral").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("4").desDescricao("Aplicativo governamental - Microempreendedor Individual(MEI)").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("5").desDescricao("Aplicativo governemental - Segurado Especial").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaTipoAcao")
	public List<ParCodigoVO> getListaTipoAcao() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("I").desDescricao("Inclusão").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("A").desDescricao("Alteração").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("E").desDescricao("Exclusão").build());
	    return listaCodigo;
	}
	
	/**
	 * Fim Cabeçalho
	 */
	
	
	
	
	
	
	
	/**
	 * Início Bloco Empregador 
	 */
	
	@ModelAttribute("listaTipoInscricao")
	public List<ParCodigoVO> getListaTipoInscricao() {
	    return codigoDAO.findAllByCodNum(5);
	}
	
	@ModelAttribute("listaClassificaoTributaria")
	public List<ParCodigoVO> getListaClassificaoTributaria() {
//		List<ParCodigoVO> listaClassificacao = codigoDAO.findAllByCodNum(8);
//		List<ParCodigoVO> novaListaClassificacao = new ArrayList<ParCodigoVO>();
//		for(ParCodigoVO vo : listaClassificacao) {
//			if(vo.getCodPar().length() != 1) {
//				novaListaClassificacao.add(vo);
//			}
//		}
//	    return novaListaClassificacao;
		return codigoDAO.findAllByCodNum(8);
	}
	
	@ModelAttribute("listaNaturezaJuridica")
	public List<ParCodigoVO> getListaNaturezaJuridica() {
	    
		List<ParCodigoVO> listaClassificacao = codigoDAO.findAllByCodNum(21);
		List<ParCodigoVO> novaListaClassificacao = new ArrayList<ParCodigoVO>();
		for(ParCodigoVO vo : listaClassificacao) {
			if(vo.getCodPar().length() != 1) {
				novaListaClassificacao.add(vo);
			}
		}
	    return novaListaClassificacao;
	}
	
	@ModelAttribute("listaCooperativa")
	public List<ParCodigoVO> getListaCooperativa() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("0").desDescricao("Não é cooperativa").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Cooperativa de Trabalho").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("2").desDescricao("Cooperativa de Produção").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("3").desDescricao("Outras Cooperativas").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaConstrutora")
	public List<ParCodigoVO> getListaConstrutora() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("0").desDescricao("Não é Construtora").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Empresa Construtora").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaDesoneracaoFolha")
	public List<ParCodigoVO> getListaDesoneracaoFolha() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("0").desDescricao("Não Aplicável").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Empresa enquadrada nos art. 7º a 9º da Lei 12.546/2011.").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaPontoEletronico")
	public List<ParCodigoVO> getListaPontoEletronico() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("0").desDescricao("Não optou pelo registro eletrônico de empregados").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Optou pelo registro eletrônico de empregados.").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaEntidadeSemFinsLucrativos")
	public List<ParCodigoVO> getListaEntidadeSemFinsLucrativos() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("N").desDescricao("Não").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("S").desDescricao("Sim").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaEmpresaTrabalhoTemporario")
	public List<ParCodigoVO> getListaEmpresaTrabalhoTemporario() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("N").desDescricao("Não").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("S").desDescricao("Sim").build());
	    return listaCodigo;
	}
	/**
	 * Fim Bloco Empregador 
	 */
	
	
	
	
	
	/**
	 * Bloco Órgão Público
	 */
	@ModelAttribute("listaUnidadeGestoraRPPS")
	public List<ParCodigoVO> getListaUnidadeGestoraRPPS() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("S").desDescricao("Sim").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("N").desDescricao("Não").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaEsferaAdministrativa")
	public List<ParCodigoVO> getListaEsferaAdministrativa() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Federal").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("2").desDescricao("Estadual ou distrital").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("3").desDescricao("Municipal").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaPoderInstituicao")
	public List<ParCodigoVO> getListaPoderInstituicao() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Executivo").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("2").desDescricao("Judiciário").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("3").desDescricao("Legislativo").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("4").desDescricao("Ministérico Público").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("5").desDescricao("Tribunal de Contas").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("6").desDescricao("Defensoria Pública").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaEnteFederativoResponsavel")
	public List<ParCodigoVO> getListaEnteFederativoResponsavel() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("S").desDescricao("É EFR").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("N").desDescricao("Não é EFR").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaPossuiRegimeProprioRPPS")
	public List<ParCodigoVO> getListaPossuiRegimeProprioRPPS() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("S").desDescricao("Sim").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("N").desDescricao("Não").build());
	    return listaCodigo;
	}
	
	
	@ModelAttribute("listaInstituiuRegimePrevidenciaComplementar")
	public List<ParCodigoVO> getListaInstituiuRegimePrevidenciaComplementar() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("S").desDescricao("Sim").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("N").desDescricao("Não").build());
	    return listaCodigo;
	}
	
	/**
	 * Fim Bloco Órgão Público
	 */


	
	
	/**
	 * Bloco Outras Informações
	 */
	@ModelAttribute("listaPossuiAcordoInternacional")
	public List<ParCodigoVO> getListaPossuiAcordoInternacional() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("0").desDescricao("Sem acordo").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Com acordo").build());
	    return listaCodigo;
	}
	
	@ModelAttribute("listaSituacaoPessoaJuridica")
	public List<ParCodigoVO> getListaSituacaoPessoaJuridica() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("0").desDescricao("Situação Normal").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Extinção").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("2").desDescricao("Fusão").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("3").desDescricao("Cisão").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("4").desDescricao("Incorporação").build());
	    
	    return listaCodigo;
	}
	
	@ModelAttribute("listaSituacaoPessoaFisica")
	public List<ParCodigoVO> getListaSituacaoPessoaFisica() {
		List<ParCodigoVO> listaCodigo = new ArrayList<ParCodigoVO>();
	    listaCodigo.add(ParCodigoVO.builder().codPar("0").desDescricao("Situação Normal").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("1").desDescricao("Encerramento de espólio").build());
	    listaCodigo.add(ParCodigoVO.builder().codPar("2").desDescricao("Saída do país em caráter permanente").build());
	    return listaCodigo;
	}
	
	/**
	 * Fim Bloco Outras Informações
	 */
	
	
	
	
	public class TriatFormValidator implements Validator {

		   @Override
		   public boolean supports (Class<?> clazz) {
		       return TriatForm.class.equals(clazz);
		   }

		   @Override
		   public void validate (Object target, Errors errors) {
			   try {
			   TriatForm form = (TriatForm) target;
			   
			   if(!form.getIdeperiodoInivalid().isEmpty()) {
				   if(!DataUtil.isDataYYYYMMValida(form.getIdeperiodoInivalid())){
					   errors.rejectValue("ideperiodoInivalid", "","* A Data de Início de Vigência está inválida.");
				   }
			   }
			   
			   if(!form.getIdeperiodoFimvalid().isEmpty()) {
				   if(!DataUtil.isDataYYYYMMValida(form.getIdeperiodoFimvalid())){
					   errors.rejectValue("ideperiodoFimvalid", "","* A Data de Fim de Vigência está inválida.");
				   }
			   }
			   
			   //Bloco Aba Empregador
			   if(!form.getDadosisencaoDtemiscertif().isEmpty()) {
				   if(!DataUtil.isDataDDMMYYYYValida(form.getDadosisencaoDtemiscertif())){
					   errors.rejectValue("dadosisencaoDtemiscertif", "","* A Data de Emissão do Certificado está inválida.");
				   }
			   }
			   
			   if(!form.getDadosisencaoDtvenccertif().isEmpty()) {
				   if(!DataUtil.isDataDDMMYYYYValida(form.getDadosisencaoDtvenccertif())){
					   errors.rejectValue("dadosisencaoDtvenccertif", "","* A Data de Vencimento do Certificado está inválida.");
				   }
			   }
			   
			   
			   if(!form.getDadosisencaoDtprotrenov().isEmpty()) {
				   if(!DataUtil.isDataDDMMYYYYValida(form.getDadosisencaoDtprotrenov())){
					   errors.rejectValue("dadosisencaoDtprotrenov", "","* A Data do Protocolo de Renovação está inválida.");
				   }
			   }
			   
			   if(!form.getDadosisencaoDtdou().isEmpty()) {
				   if(!DataUtil.isDataDDMMYYYYValida(form.getDadosisencaoDtdou())){
					   errors.rejectValue("dadosisencaoDtdou", "","* A Data Publicação Diário Oficial da União está inválida.");
				   }
			   }
			   
			   if(!form.getContatoCpfctt().isEmpty()) {
				   if(!new GeraCpfCnpj().isCPF(form.getContatoCpfctt())) {
					   errors.rejectValue("contatoCpfctt", "","* O CPF do Contato é inválido.");
				   }
			   }
			   
			   
			   //Aba Contato
			   if(!form.getContatoEmail().isEmpty()) {
				   if(!StringUtil.isEmailValido(form.getContatoEmail())) {
					   errors.rejectValue("contatoEmail", "","* O E-mail do Contato é inválido.");
				   }
			   }
			   

			   
			   //Aba Órgão Público
			   if(!form.getInfoopCnpjefr().isEmpty()) {
				   if(!new GeraCpfCnpj().isCNPJ(form.getInfoopCnpjefr().replaceAll("\\.", "").replaceAll("-","").replaceAll("-", "").replaceAll("/", ""))) {
					   errors.rejectValue("infoopCnpjefr", "","* O CNPJ do Ente Federativo Responsável é inválido.");
				   }
			   }
			   
			   
			   //Aba Software
			   if(!form.getSoftwarehouseEmail().isEmpty()) {
				   if(!StringUtil.isEmailValido(form.getSoftwarehouseEmail())) {
					   errors.rejectValue("softwarehouseEmail", "","* O E-mail é inválido.");
				   }
			   }
			   
			   if(!form.getSoftwarehouseCnpjsofthouse().isEmpty()) {
				   if(!new GeraCpfCnpj().isCNPJ(form.getSoftwarehouseCnpjsofthouse().replaceAll("\\.", "").replaceAll("-","").replaceAll("-", "").replaceAll("/", ""))) {
					   errors.rejectValue("softwarehouseCnpjsofthouse", "","* O CNPJ da Empresa é inválido.");
				   }
			   }
			   
			   
			   
			   
//			   
			   //Terceira Validação
			   if(!errors.hasErrors()) {
					   if(!form.getIdeperiodoInivalid().isEmpty() && !form.getIdeperiodoFimvalid().isEmpty()) {
								if(DataUtil.isPrimeiraDataMaiorQueSegundaData(DataUtil.converterDataYYYYMM(form.getIdeperiodoInivalid()), DataUtil.converterDataYYYYMM(form.getIdeperiodoFimvalid()))) {
									errors.rejectValue("ideperiodoFimvalid", "","* A Data Fim de Vigência não pode ser menor que a Data Início da Vigência.");
								}
						 }
					   
					   if(!form.getIdeperiodoInivalid().isEmpty()) {
						   if(triatService.isDataMenorQueDataImplantacaoDoSistemaEsocial(form.getIdeperiodoInivalid())) {
							   errors.rejectValue("ideperiodoInivalid", "","* O início de vigência não pode ser anterior ao período início da implantação do eSocial");
						   }
					   }
			   }
			   
			   
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
			   
//		       ValidationUtils.rejectIfEmpty(errors, "dataEnvio", "date.empty","aew");
		   }
		}
	
}
