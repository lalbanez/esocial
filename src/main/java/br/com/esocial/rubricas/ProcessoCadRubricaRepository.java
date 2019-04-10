package br.com.esocial.rubricas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoCadRubricaRepository extends JpaRepository<ProcessoCadRubrica, Integer>{
	
	List<ProcessoCadRubrica> findAllByIdCadRub(Integer idCadRubrica);
	
//	Long deleteByIdCadRubrica(Integer idCadRubrica);
	
}
