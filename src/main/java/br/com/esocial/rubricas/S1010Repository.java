package br.com.esocial.rubricas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S1010Repository extends JpaRepository<S1010, Integer> {

	S1010 findFirstByiderubricaCodrubrOrderBySeqEventoDesc(Integer codRubrica);

}
