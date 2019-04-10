package br.com.esocial.repository.par;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esocial.entity.par.ParOrigemVO;

public interface ParOrigemRepository extends JpaRepository<ParOrigemVO, Integer> {
	List<ParOrigemVO> findAll();
}
