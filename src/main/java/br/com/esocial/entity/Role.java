package br.com.esocial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ESOCIAL_ROLE")
public class Role {
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "idRole")
	@SequenceGenerator(name = "idRole", sequenceName = "SEQ_ESOCIAL_ROLE" )
    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;
}
