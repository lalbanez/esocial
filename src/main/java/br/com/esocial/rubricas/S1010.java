package br.com.esocial.rubricas;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.esocial.estrutural.util.StatusEventEnum;
import br.com.esocial.estrutural.util.TipoAcao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "TSOC_1010_RUBRICA", schema = "ESOCIAL")
public class S1010 {

	@Id
	@GeneratedValue
	private Integer idPk;
	private Integer cod_ins;
	private String id;
	private Integer tpamb; 
	private Integer procemi;
	private String verproc; 
	private Integer tpinsc; 
	private String nrinsc;
	private Integer iderubricaCodrubr; 
	private String iderubricaIdetabrubr;
	private String iderubricaInivalid; 
	private String iderubricaFimvalid; 
	private String dadosrubrica_dscrubr; 
	private Integer dadosrubrica_natrubr; 
	private Integer dadosrubrica_tprubr;  
	private String dadosrubrica_codinccp; 
	private String dadosrubrica_codincirrf; 
	private String dadosrubrica_codincfgts; 
	private String dadosrubrica_codincsind; 
	private String dadosrubrica_observacao; 
	private String novavalidade_inivalid; 
	private String novavalidade_fimvalid; 
	private Integer id_origem;
	private Integer id_lote;
	
	@Enumerated(EnumType.STRING)
	private StatusEventEnum ctr_flg_status;
	private Calendar dat_ing;
	private Calendar dat_ult_atu;
	private String nom_usu_ult_atu;
	private String nom_pro_ult_atu;
	private String xml_envio;
	private String flg_vigencia;
	private Integer id_evento;
	private Integer id_periodo_det;
	private String dadosrubrica_codinccprp; 
	private String dadosrubrica_tetoremun; 
	
	@Enumerated(EnumType.STRING)	
	private TipoAcao ctr_flg_acao; 
	private Integer id_cad_rub;
	private Integer seqEvento;
	private Integer ws_cod_resposta;
	private String ws_dsc_resposta;
	private String ws_dh_proc;
	private String ws_ver_app_proc;
	
}
