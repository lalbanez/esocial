package br.com.esocial.repository.par;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esocial.entity.par.ParSigePrevEsocialVO;

public interface ParSigePrevEsocialRepository extends JpaRepository<ParSigePrevEsocialVO, Integer> {
	List<ParSigePrevEsocialVO> findCodEsocialandDesEsocialDistinctByCodTipoOrderByCodEsocial(Integer codTipo);
}
