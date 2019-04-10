package br.com.esocial.repository.ctr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esocial.entity.ctr.CtrPeriodoDetVO;

public interface CtrPeriodoDetRepository extends JpaRepository<CtrPeriodoDetVO, Integer> {

	List<CtrPeriodoDetVO> findAll();
	
	
	CtrPeriodoDetVO findByIdEventoAndFlgStatus(Integer idEvento, Character flgStatus);

}
