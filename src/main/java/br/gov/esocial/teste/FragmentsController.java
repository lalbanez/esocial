package br.gov.esocial.teste;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.esocial.entity.par.ParSigePrevEsocialVO;
import br.com.esocial.rubricas.ProcessoCadRubrica;

@Controller
public class FragmentsController {
 
    @GetMapping("/fragments")
    public String getHome() {
        return "fragments.html";
    }
 
    @GetMapping("/markup")
    public String markupPage() {
        return "markup.html";
    }
 
    @GetMapping("/params")
    public String paramsPage() {
        return "params.html";
    }
 
    @GetMapping("/other")
    public String otherPage(Model model) {
    	ProcessoCadRubrica processoCadRubrica = new ProcessoCadRubrica();
		
		processoCadRubrica.setTipProcesso(1);
		processoCadRubrica.setTpproc(1);
		processoCadRubrica.setNrproc("2002020215635");
		processoCadRubrica.setExtdecisao(1);
		processoCadRubrica.setCodsusp(1);
		
		List<ProcessoCadRubrica> processoCadRubricas = new ArrayList<>();
		processoCadRubricas.add(processoCadRubrica);
    	
        model.addAttribute("data", processoCadRubricas);
        return "other.html";
    }
}