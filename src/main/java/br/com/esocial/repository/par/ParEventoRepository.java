package br.com.esocial.repository.par;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esocial.entity.par.ParEventoVO;

public interface ParEventoRepository extends JpaRepository<ParEventoVO, Integer> {

	List<ParEventoVO> findAll();
	
	List<ParEventoVO> findAllByTipEvento(String tipEvento);
	
	

}
