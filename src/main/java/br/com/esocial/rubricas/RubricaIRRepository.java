package br.com.esocial.rubricas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricaIRRepository extends JpaRepository<RubricaIR, Integer>{
	
	RubricaIR findFirstByCodRubrica(Integer codRubrica);
	
}
