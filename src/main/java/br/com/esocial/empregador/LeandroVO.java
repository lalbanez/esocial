package br.com.esocial.empregador;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ESOCIAL_PERSONAGEM", schema = "ESOCIAL")
public class LeandroVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencePersonagemSeq")
	@SequenceGenerator(name = "sequencePersonagemSeq", sequenceName = "PERSONAGEM_SEQ")
	private Integer idPersonagem;

	private String nomePersonagem;
	private Integer levelPersonagem;

	@ManyToOne
	@JoinColumn(name = "idClaPersonagem")
	private LeandroCla leandroCla;

//	private Integer idCla;	

}
