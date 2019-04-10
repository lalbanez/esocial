package br.com.esocial.rubricas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S1010HCadRepository extends JpaRepository<S1010HCad, Integer>{
	S1010HCad findFirstByCodRubricaOrderBySeqHistDesc(Integer codRubrica);
}
