package br.com.esocial.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class ESocialDataSource extends HikariConfig{

   @Value("${spring.datasource.username}")
    private String usuario;

   @Value("${spring.datasource.password}")
    private String senha;

	@Bean
	public DataSource  dataSource() {
		
		//TODO separar a senha criptograda em arquivo externo.
//		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//		encryptor.setPassword("chaveAtlantic"); //TODO essa chave deverá colocar no System variables quando subir para producao.
//		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
////		
		HikariConfig  hikari = new HikariConfig();
//		hikari.setUsername(encryptor.decrypt(usuario));
//		hikari.setPassword(encryptor.decrypt(senha));
		hikari.setUsername("ljunior");
		hikari.setPassword("123456");
		hikari.setJdbcUrl("jdbc:oracle:thin:@//10.32.36.24:1521/pdbspprvhmg.spprev.gov");
		return new HikariDataSource(hikari);
	}

}

