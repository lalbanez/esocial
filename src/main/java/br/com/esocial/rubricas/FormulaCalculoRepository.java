package br.com.esocial.rubricas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaCalculoRepository extends JpaRepository<FormulaCalculo, Integer>{
	
	FormulaCalculo findFirstIndGravaDetalheByCodRubrica(Integer codRubrica);
	
}
