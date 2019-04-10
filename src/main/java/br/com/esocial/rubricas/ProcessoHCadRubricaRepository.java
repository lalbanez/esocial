package br.com.esocial.rubricas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoHCadRubricaRepository extends JpaRepository<ProcessoHCadRubrica, Integer>{
	
	List<ProcessoHCadRubrica> findAllByIdCadRub(Integer idCadRubrica);
	
}
