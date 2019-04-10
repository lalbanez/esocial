package br.com.esocial.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import br.com.esocial.estrutural.util.MascaraUtil;
import br.com.esocial.estrutural.util.StatusEventoEnum;
import br.com.esocial.estrutural.util.StringUtil;
import br.com.esocial.form.MonitoramentoConsultaDTO;
import br.com.esocial.form.MonitoramentoEventoDTO;

@Repository
public class MonitoramentoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public MonitoramentoEventoDTO pesquisarMonitoramento(String nomeTabela, String idPeriodo) {
		String query =  "SELECT count(*) TOTAL,   "
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'EV' then 1 else 0 end), 0)  ERRO_VALIDACAO, "
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'AX' then 1 else 0 end), 0)  AGUARDANDO_GERACAO_XML,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'EX' then 1 else 0 end), 0)  ERRO_GERACAO_XML,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'AA' then 1 else 0 end), 0)  AGUARDANDO_ASSINATURA,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'EA' then 1 else 0 end), 0)  ERRO_ASSINATURA,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'AL' then 1 else 0 end), 0)  AGUARDANDO_GERACAO_LOTE,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'EL' then 1 else 0 end), 0)  ERRO_GERACAO_LOTE,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'AE' then 1 else 0 end), 0)  AGUARDANDO_ENVIO,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'EE' then 1 else 0 end), 0)  ERRO_NO_ENVIO,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'AR' then 1 else 0 end), 0)  ENVIADO,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'RE' then 1 else 0 end), 0)  RETORNADO_COM_ERRO,"
				+ " NVL(SUM(case when BENINI.CTR_FLG_STATUS = 'FN' then 1 else 0 end), 0)  FINALIZADO"
				+ " FROM ESOCIAL." + nomeTabela + " BENINI "
		        +    " INNER JOIN ESOCIAL.TSOC_CTR_PERIODO_DET PERDET"
		          +    " ON BENINI.ID_PERIODO_DET = PERDET.ID_PERIODO_DET"
		          +    " AND PERDET.ID_PERIODO = " + idPeriodo
	          	+    " AND BENINI.FLG_VIGENCIA = 'A'";
		
		MonitoramentoEventoDTO monitoramento  = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(MonitoramentoEventoDTO.class));
		return monitoramento;
	}
		 
	
	
	public List<MonitoramentoEventoDTO> pesquisarEventosPor(String idOrigem, String idPeriodo, String tipoEvento, String idEvento) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT PERDET.ID_ORIGEM, PERDET.ID_PERIODO, PERDET.ID_EVENTO, EVENTO.NOM_TABELA, PER.PERIODO DESC_PERIODO, ORIG.DESC_ORIGEM, ");
		sb.append("  EVENTO.COD_EVENTO,");
		sb.append("  EVENTO.DESC_EVENTO,");
		sb.append(" EVENTO.TIP_EVENTO");
		sb.append(" FROM ESOCIAL.TSOC_CTR_PERIODO_DET PERDET");
		sb.append("  INNER JOIN ESOCIAL.TSOC_PAR_EVENTO EVENTO");
		sb.append(" ON PERDET.ID_EVENTO = EVENTO.ID_EVENTO");
		
		sb.append("  INNER JOIN ESOCIAL.TSOC_CTR_PERIODO PER");
		sb.append(" ON PERDET.ID_PERIODO = PER.ID_PERIODO");
		
		sb.append("   INNER JOIN ESOCIAL.TSOC_PAR_ORIGEM ORIG");
		sb.append(" ON ORIG.ID_ORIGEM = PERDET.ID_ORIGEM");	
		
		sb.append(" WHERE PERDET.ID_ORIGEM = ? ");
		
		if(!idPeriodo.isEmpty()) {
			sb.append(" AND PERDET.ID_PERIODO = ? ");
		}
		
		if(!tipoEvento.isEmpty()) {
			sb.append(" AND EVENTO.TIP_EVENTO = ?");
		}
		
		if(!idEvento.isEmpty()) {
			sb.append(" AND PERDET.ID_EVENTO = ? ");
		}
		
		PreparedStatementSetter parametros  = new PreparedStatementSetter() {
			int contador = 0;
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(++contador, idOrigem);
			    
				if(!idPeriodo.isEmpty()) {
					ps.setString(++contador, idPeriodo);
				}
				
				if(!tipoEvento.isEmpty()) {
					ps.setString(++contador, tipoEvento);
				}
				
				if(!idEvento.isEmpty()) {
					ps.setString(++contador, idEvento);
				}
		    }
		};
		
		List<MonitoramentoEventoDTO> listaEventos  = jdbcTemplate.query(sb.toString(), parametros, new BeanPropertyRowMapper<>(MonitoramentoEventoDTO.class));
		return listaEventos;
	}
	
	
	public List<MonitoramentoConsultaDTO> pesquisarDetalhesEventoConsulta(String idOrigem, String idPeriodo, String evento,
			String idEvento, String numLote, String dataEnvio, String protocoloEnvio, String dataRetorno, String numRecibo, String dataGeracao,
			String numCpf, String status, String grupoStatus){
			
		
	    String sql = "select 'ESOCIAL.' || nom_tabela from ESOCIAL.TSOC_PAR_EVENTO where id_evento = ?";
	    String nomeTabela = (String) jdbcTemplate.queryForObject(sql, new Object[] { evento }, String.class);

		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT PER.PERIODO periodo, ORIG.DESC_ORIGEM ORIGEM , EVENTO.DESC_EVENTO EVENTO, EVENTO.COD_EVENTO COD_EVENTO,");
		sb.append("  COD.DES_DESCRICAO STATUS_EVENTO, BENINI.ID ID_EVENTO ,  ");
		
		if(nomeTabela.equalsIgnoreCase("esocial.tsoc_1010_rubrica")) {
			sb.append("  BENINI.IDERUBRICA_CODRUBR COD_RUBRICA ,  ");
		}
		
		sb.append("LOTE.WS_PROTOCOLO_ENVIO PROTOCOLO_ENVIO, ");
		sb.append(" LOTE.WS_PROTOCOLO_RETORNO  NUM_RECIBO, ");
		
		sb.append("  TO_CHAR(BENINI.DAT_ING, 'DD/MM/YYYY') DATA_GERACAO , ");
		sb.append("  TO_CHAR(LOTE.CTR_DAT_ENVIO, 'DD/MM/YYYY') DATA_ENVIO, ");
		sb.append("TO_CHAR( LOTE.CTR_DAT_RETORNO, 'DD/MM/YYYY') DATA_RETORNO, ");
		sb.append(" ORIG.ID_EMPREGADOR  ID_EMPREGADOR ");
		
		sb.append(" FROM ").append(nomeTabela).append(" BENINI ");
		sb.append("   LEFT JOIN ESOCIAL.TSOC_CTR_LOTE LOTE ");
		sb.append("      ON BENINI.ID_LOTE = LOTE.ID_LOTE ");
		
		sb.append("    LEFT JOIN ESOCIAL.TSOC_CTR_PERIODO_DET PERDET ");
		sb.append("       ON PERDET.ID_PERIODO_DET = BENINI.ID_PERIODO_DET ");

		sb.append(" LEFT JOIN ESOCIAL.TSOC_PAR_ORIGEM ORIG ");
		sb.append("   ON PERDET.ID_ORIGEM = ORIG.ID_ORIGEM ");
		
		sb.append("    LEFT JOIN ESOCIAL.TSOC_CTR_PERIODO PER ");
		sb.append("       ON PERDET.ID_PERIODO = PER.ID_PERIODO ");
		
		sb.append("     LEFT JOIN ESOCIAL.TSOC_PAR_EVENTO EVENTO ");
		sb.append("         ON EVENTO.ID_EVENTO = PERDET.ID_EVENTO");
		
		sb.append("    LEFT JOIN ESOCIAL.TSOC_PAR_CODIGO COD ");
		sb.append("         ON COD.COD_NUM = 1002 ");
		sb.append("         AND COD.COD_PAR =  BENINI.CTR_FLG_STATUS ");
		
		sb.append("   WHERE PERDET.ID_ORIGEM = ? ");
		sb.append("      AND PERDET.ID_PERIODO = ? ");
		sb.append("    AND PERDET.ID_EVENTO  =  ? "); // tipo de evento
		
		sb.append("    AND BENINI.FLG_VIGENCIA = 'A' ");
		

		if(!idEvento.isEmpty()) {
			sb.append("  AND UPPER(BENINI.ID)  LIKE ? ");
		}
		
		if(!numLote.isEmpty()) {
			sb.append("  AND LOTE.ID_LOTE   =  ? ");
		}
		
		if(!dataEnvio.isEmpty()) {
			sb.append("   AND LOTE.CTR_DAT_ENVIO  =  ?");
		}
		
		if(!protocoloEnvio.isEmpty()) {
			sb.append("   AND LOTE.CTR_IDE_PROTOCOLO_ENVIO  =  ? ");
		}
		
		if(!dataRetorno.isEmpty()) {
			sb.append("    AND LOTE.CTR_DAT_RETORNO  =   ? ");
		}
		
		if(!numRecibo.isEmpty()) {
			sb.append("   AND LOTE.CTR_IDE_RETORNO  = ?  ");
		}
		
		if(!dataGeracao.isEmpty()) {
			sb.append("     AND BENINI.DAT_ING = ?  ");
		}
		
		if(!numCpf.isEmpty()) {
			sb.append("   AND BENINI.BENEFICIARIO_CPFBENEF  = ? ");
		}
		
		sb.append("    AND BENINI.CTR_FLG_STATUS  IN ");
		if(!status.isEmpty()) {
			sb.append("(?)");
		}else {
			sb.append(StringUtil.obterInterrogacoesParametros(StatusEventoEnum.obterStatusEventoPorGrupoStatus(grupoStatus).obterListaTipoStatus()));
		}
		
		
		PreparedStatementSetter parametros  = new PreparedStatementSetter() {
			int contador = 0;
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(++contador, idOrigem);
				ps.setString(++contador, idPeriodo);
				ps.setString(++contador, evento);
				
				if(!idEvento.isEmpty()) {
					ps.setString(++contador, "%" + idEvento.toUpperCase() + "%");
				}
				if(!numLote.isEmpty()) {
					ps.setString(++contador, numLote);
				}
				if(!dataEnvio.isEmpty()) {
					ps.setString(++contador, dataEnvio);
				}
				if(!protocoloEnvio.isEmpty()) {
					ps.setString(++contador, protocoloEnvio);
				}
				if(!dataRetorno.isEmpty()) {
					ps.setString(++contador, dataRetorno);
				}
				if(!numRecibo.isEmpty()) {
					ps.setString(++contador, numRecibo);
				}
				if(!dataGeracao.isEmpty()) {
					ps.setString(++contador, dataGeracao);
				}
				if(!numCpf.isEmpty()) {
					ps.setString(++contador, MascaraUtil.retiraMascaraCpf(numCpf));
				}
				
				if(!status.isEmpty()) {
					ps.setString(++contador, status);
				}else {
					    List<String> valores = StatusEventoEnum.obterStatusEventoPorGrupoStatus(grupoStatus).obterListaTipoStatus();
						for(String valor : valores) {
							ps.setString(++contador, valor);
						}
				}
				
			
				
		    }
		};
		List<MonitoramentoConsultaDTO> listaEventos  = jdbcTemplate.query(sb.toString(), parametros, new BeanPropertyRowMapper<>(MonitoramentoConsultaDTO.class));
		return listaEventos;
	}
	
	
	
	
		 
//		 int result = jdbcTemplate.queryForObject(
//				    "SELECT count(*) TOTAL FROM TSOC_2400_BENEFICIARIO_INI", Integer.class);
////		    Query query = em.createNativeQuery("SELECT * from  ESOCIAL.TSOC_2400_BENEFICIARIO_INI");
////		    return (List<MonitoramentoEventoDTO>) query.getResultList();
//	  }
//	
	
	
//	 extends JpaRepository<User, Long>
	
	
	//https://www.concretepage.com/spring-5/spring-data-crudrepository-example
	//https://www.baeldung.com/hibernate-many-to-many
	
//	@Query(
//			  value = "SELECT * FROM Users u WHERE u.status = ?1", 
//			  nativeQuery = true)
//			String findUserByStatusNative(Integer status);
	
	
	
//	https://www.baeldung.com/spring-data-jpa-query
	
}
