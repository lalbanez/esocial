package br.com.esocial.repository.ctr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.esocial.entity.ctr.CtrPeriodoVO;

public interface CtrPeriodoRepository extends JpaRepository<CtrPeriodoVO, Integer> {

	List<CtrPeriodoVO> findAll();
	
	@Query(value = "SELECT DISTINCT PER.* "
			+ "FROM ESOCIAL.TSOC_CTR_PERIODO PER"
			+ "  INNER JOIN ESOCIAL.TSOC_CTR_PERIODO_DET DET"
			+ "  ON PER.ID_PERIODO = DET.ID_PERIODO"
			+ "    AND DET.ID_ORIGEM = ?1"
			+ " WHERE PER.FLG_STATUS = 'A'"
			+ " ORDER BY TO_DATE(periodo, 'MM/YYYY') DESC",	
			 
		   nativeQuery = true)
	List<CtrPeriodoVO> pesquisarPeriodosEmAbertoPorOrigem(String idOrigem);
	
	
	//https://www.concretepage.com/spring-5/spring-data-crudrepository-example
	//https://www.baeldung.com/hibernate-many-to-many
	
//	@Query(
//			  value = "SELECT * FROM Users u WHERE u.status = ?1", 
//			  nativeQuery = true)
//			String findUserByStatusNative(Integer status);
	
	
	
//	https://www.baeldung.com/spring-data-jpa-query
	
//	SELECT count(*) TOTAL,
//    SUM(case when CTR_FLG_STATUS = 'EV' then 1 else 0 end) ERRO_VALIDACAO,
//    SUM(case when CTR_FLG_STATUS = 'AX' then 1 else 0 end) AGUARDANDO_GERACAO_XML,
//    SUM(case when CTR_FLG_STATUS = 'EX' then 1 else 0 end) ERRO_GERACAO_XML,
//    SUM(case when CTR_FLG_STATUS = 'AA' then 1 else 0 end) AGUARDANDO_ASSINATURA,
//    SUM(case when CTR_FLG_STATUS = 'EA' then 1 else 0 end) ERRO_ASSINATURA,
//
//    SUM(case when CTR_FLG_STATUS = 'AL' then 1 else 0 end) AGUARDANDO_GERACAO_LOTE,
//    SUM(case when CTR_FLG_STATUS = 'EL' then 1 else 0 end) ERRO_GERACAO_LOTE,
//    SUM(case when CTR_FLG_STATUS = 'AE' then 1 else 0 end) AGUARDANDO_ENVIO,
//    SUM(case when CTR_FLG_STATUS = 'EE' then 1 else 0 end) ERRO_NO_ENVIO,
//    SUM(case when CTR_FLG_STATUS = 'EN' then 1 else 0 end) ENVIADO,
//    SUM(case when CTR_FLG_STATUS = 'RE' then 1 else 0 end) RETORNADO_COM_ERRO,
//    SUM(case when CTR_FLG_STATUS = 'FN' then 1 else 0 end) FINALIZADO
//FROM TSOC_2400_BENEFICIARIO_INI;

}
