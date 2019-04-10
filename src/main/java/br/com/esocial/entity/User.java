package br.com.esocial.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.esocial.entity.User.PrimeiraValidacao;
import br.com.esocial.entity.User.SegundaValidacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GroupSequence({ PrimeiraValidacao.class, SegundaValidacao.class, User.class})
@Entity
@Table(name = "TB_ESOCIAL_USER")
public class User {
	
    interface PrimeiraValidacao {}
    interface SegundaValidacao {}

    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "SEQ_ESOCIAL_USER" )
    @Column(name = "user_id")
    private int id;
    
    @Column(name = "email")
    @NotEmpty(message = "*Preencha o campo de e-mail", groups = PrimeiraValidacao.class)
    @Email(message = "*Preencha o campo com um e-mail válido", groups = PrimeiraValidacao.class)
    private String email;
    
    @Column(name = "password")
    @NotEmpty(message = "*Preencha o campo de senha", groups = PrimeiraValidacao.class )
    @Length(min = 5, message = "*Sua senha deve ter no mínimo 5 caracteres" , groups = SegundaValidacao.class)
    private String password;
    
    @Column(name = "name")
    @NotEmpty(message = "*Preencha o seu nome" , groups = PrimeiraValidacao.class)
    private String name;
    
    @Column(name = "last_name")
    @NotEmpty(message = "*Preencha o seu sobrenome" , groups = PrimeiraValidacao.class)
    private String lastName;
    
    @Column(name = "active")
    private int active;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_esocial_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
