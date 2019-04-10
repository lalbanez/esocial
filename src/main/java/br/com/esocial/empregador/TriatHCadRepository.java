package br.com.esocial.empregador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriatHCadRepository  extends JpaRepository<TriatHCadVO, Integer> {
	
}


