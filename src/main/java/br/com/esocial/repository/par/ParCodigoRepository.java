package br.com.esocial.repository.par;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esocial.entity.par.ParCodigoVO;

public interface ParCodigoRepository extends JpaRepository<ParCodigoVO, Integer> {
	
	List<ParCodigoVO> findAllByCodNum(Integer codNum);
	
	List<ParCodigoVO> findAllByIdDefinicao(Integer codNum);
}
