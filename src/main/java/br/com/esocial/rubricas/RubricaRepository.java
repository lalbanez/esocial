package br.com.esocial.rubricas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricaRepository extends JpaRepository<Rubrica, Integer>{
	
	Rubrica findFirstByCodRubricaOrderByCodEntidade(Integer codRubrica);
	
}
