package br.com.esocial.empregador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TriatEventoRepository  extends JpaRepository<TriatEventoVO, Integer> {
	
	TriatEventoVO findByFlgVigencia(Character flgVigencia);
	
	@Modifying
	@Query("update TriatEventoVO u set u.flgVigencia = ?1")
	void updateAllFlgVigencia(Character flgVigencia);
	
}


