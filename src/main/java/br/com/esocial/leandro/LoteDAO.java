package br.com.esocial.leandro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.esocial.configuration.ESocialDataSource;

@Repository
public class LoteDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<LoteVO> consultarCrontab(String numCategoria) {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT distinct");
		sb.append("    a.id_ctr_processo, ");
		sb.append("    b.id_evento, ");
		sb.append("    b.par_minuto, ");
		sb.append("    b.par_hora, ");
		sb.append("    b.par_dia, ");
		sb.append("    b.par_mes, ");
		sb.append("    b.par_semana ");
		sb.append(" FROM");
		sb.append("    ESOCIAL.tsoc_ctr_processo a, ");
		sb.append("    ESOCIAL.tsoc_par_processo b, ");
		sb.append("    ESOCIAL.tsoc_par_programa c, ");
		sb.append("    ESOCIAL.tsoc_ctr_lote d ");
		sb.append(" WHERE");
		sb.append("    a.id_processo = b.id_processo ");
		sb.append("    AND c.id_programa = b.id_programa ");
		sb.append("    AND d.id_evento = b.id_evento ");
		sb.append("    AND c.num_categoria = " + numCategoria);

		List<LoteVO> listaLote = jdbcTemplate.query(sb.toString(), new RowMapper<LoteVO>() {
			public LoteVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				LoteVO loteVO = new LoteVO();

				String crontab = "0 " // SECONDS
						+ rs.getString("PAR_MINUTO") + " " // MINUTES
						+ rs.getString("PAR_HORA") + " " // HOURS
						+ rs.getString("PAR_DIA") + " " // DAY OF MOUNTH
						+ rs.getString("PAR_MES") + // //MOUNTH
				" ?"; // DAY OF WEEK
				loteVO.setCRONTAB_FORMAT(crontab);
				loteVO.setID_CTR_PROCESSO(rs.getString("ID_CTR_PROCESSO"));
				return loteVO;
			}
		});

		return listaLote;
	}

	public List<LoteVO> consultarLote(String numCategoria, String flgStatus) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("    a.*, ");
		sql.append("    b.id_evento, ");
		sql.append("    b.par_minuto, ");
		sql.append("    b.par_hora, ");
		sql.append("    b.par_dia, ");
		sql.append("    b.par_mes, ");
		sql.append("    b.count_check_open_row, ");
		sql.append("    d.ws_xml_envio, ");
		sql.append("    d.id_lote, ");
		sql.append("    d.xml_lote, ");
		sql.append("    d.ws_protocolo_envio ");
		sql.append(" FROM ");
		sql.append("    ESOCIAL.tsoc_ctr_processo a, ");
		sql.append("    ESOCIAL.tsoc_par_processo b, ");
		sql.append("    ESOCIAL.tsoc_par_programa c, ");
		sql.append("    ESOCIAL.tsoc_ctr_lote d ");
		sql.append(" WHERE ");
		sql.append("    a.id_processo = b.id_processo ");
		sql.append("    AND c.id_programa = b.id_programa ");
		sql.append("    AND d.id_evento = b.id_evento ");
		sql.append("    AND c.num_categoria = " + numCategoria);
		sql.append("    AND d.flg_status = '" + flgStatus + "'");
		sql.append("    AND a.flg_Status = 'A' ");
		sql.append(" ORDER BY ");
		sql.append("    d.id_lote ");

		List<LoteVO> listaLote = jdbcTemplate.query(sql.toString(), new RowMapper<LoteVO>() {
			public LoteVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				LoteVO vo = new LoteVO();
				vo.setXML_LOTE(rs.getString("XML_LOTE"));
				vo.setID_CTR_PROCESSO(rs.getString("ID_CTR_PROCESSO"));
				vo.setID_LOTE(rs.getString("ID_LOTE"));
				vo.setCOUNT_CHECK_OPEN_ROW(rs.getString("COUNT_CHECK_OPEN_ROW"));
				vo.setFLG_STATUS(rs.getString("FLG_STATUS"));
				if (numCategoria == "6") {
					vo.setWS_PROTOCOLO_ENVIO(rs.getString("WS_PROTOCOLO_ENVIO"));
					String XML_CONSULTA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
							+ "<eSocial xmlns= \"http://www.esocial.gov.br/schema/lote/eventos/envio/consulta/retornoProcessamento/v1_0_0\" >\r\n"
							+ "	<consultaLoteEventos>\r\n"
							+ " <protocoloEnvio>" + vo.getWS_PROTOCOLO_ENVIO() + "</protocoloEnvio>\r\n"
							+ "	</consultaLoteEventos>\r\n" 
							+ "</eSocial>";
					vo.setXML_LOTE(XML_CONSULTA);
				}
				return vo;
			}
		});

		return listaLote;
	}

	public LoteVO getProcesoByIdProcesso(String id_ctr_processo) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("    a.*, ");
		sql.append("    b.id_evento, ");
		sql.append("    b.par_minuto, ");
		sql.append("    b.par_hora, ");
		sql.append("    b.par_dia, ");
		sql.append("    b.par_mes, ");
		sql.append("    b.count_check_open_row, ");
		sql.append("    d.ws_xml_envio, ");
		sql.append("    d.id_lote, ");
		sql.append("    d.xml_lote, ");
		sql.append("    d.ws_protocolo_envio ");
		sql.append(" FROM ");
		sql.append("    ESOCIAL.tsoc_ctr_processo a, ");
		sql.append("    ESOCIAL.tsoc_par_processo b, ");
		sql.append("    ESOCIAL.tsoc_par_programa c, ");
		sql.append("    ESOCIAL.tsoc_ctr_lote d ");
		sql.append(" WHERE ");
		sql.append("    a.id_processo = b.id_processo ");
		sql.append("    AND c.id_programa = b.id_programa ");
		sql.append("    AND d.id_evento = b.id_evento ");
		sql.append("    AND d.id_ctr_processo = " + id_ctr_processo);
		sql.append(" ORDER BY ");
		sql.append("    d.id_lote ");

		List<LoteVO> listaLote = jdbcTemplate.query(sql.toString(), new RowMapper<LoteVO>() {
			public LoteVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				LoteVO vo = new LoteVO();
				vo.setFLG_STATUS(rs.getString("FLG_STATUS"));
				return vo;
			}
		});

		if (listaLote.isEmpty()) {
			return listaLote.get(0);
		} else {
			return null;
		}
	}

	public void atualizaLoteEsocial(LoteVO lote) {
		ESocialDataSource eds = new ESocialDataSource();
		DataSource ds = eds.dataSource();
		jdbcTemplate = new JdbcTemplate(ds);
		jdbcTemplate.update("call ESOCIAL.pac_esocial_envio.sp_envia_lote(?,?,?)", lote.getID_CTR_PROCESSO(),
				lote.getXML_WS_RETORNO(), lote.getID_LOTE());
		//TODO , lote.getRETURN_ERRO();
		System.out.println("Envio Atualizado!" + "\nProcesso: " + lote.getID_CTR_PROCESSO() + "\nLote: " + lote.getID_LOTE() + "\nXML_RETORNO: " + lote.getXML_WS_RETORNO());
	}

	public void atualizaConsultaEsocial(LoteVO lote) {
		ESocialDataSource eds = new ESocialDataSource();
		DataSource ds = eds.dataSource();
		jdbcTemplate = new JdbcTemplate(ds);
		jdbcTemplate.update("call ESOCIAL.pac_esocial_retorno.sp_retorna_lote(?,?,?,?)", lote.getID_CTR_PROCESSO(),
				lote.getXML_WS_RETORNO(), lote.getWS_PROTOCOLO_ENVIO(), lote.getRETURN_ERRO());
	}
}