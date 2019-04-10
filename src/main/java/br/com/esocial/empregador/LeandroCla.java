package br.com.esocial.empregador;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_ESOCIAL_CLA",schema="ESOCIAL")
public class LeandroCla {

	@Id
	private Integer idCla;
	private String nomeCla;	
	
	
}
