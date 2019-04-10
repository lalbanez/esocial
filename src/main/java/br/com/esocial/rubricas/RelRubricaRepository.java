package br.com.esocial.rubricas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelRubricaRepository extends JpaRepository<RelRubricas, Integer>{
	
	List<RelRubricas> findCodAgrupacaoRubricaByCodRubrica(Integer codRubrica);
	
}
