package br.com.esocial.rubricas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S1010CadRepository extends JpaRepository<S1010Cad, Integer>{
	
	S1010Cad findFirstByCodRubricaOrderBySeqCadDesc(Integer codRubrica);
	

}
