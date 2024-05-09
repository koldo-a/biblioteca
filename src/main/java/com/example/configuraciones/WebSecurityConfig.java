package com.example.configuraciones;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	// https://spring.io/guides/gs/securing-web
	// https://www.baeldung.com/spring-security-jdbc-authentication

	// AUTENTICACIÓN
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
	    auth.jdbcAuthentication().dataSource(dataSource)
	    	.usersByUsernameQuery("""
    			SELECT email,password,1 
    			FROM usuarios 
    			WHERE email = ?
			""")
	    	.authoritiesByUsernameQuery("""
    			SELECT email, CONCAT('ROLE_',rol)
    			FROM gestionpedidoscrud.usuarios
				WHERE email = ?
			""");
	}

	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	// AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(x -> x.disable()).authorizeHttpRequests(
				(requests) -> requests
					.requestMatchers("/producto").hasRole("ADMIN")
					.requestMatchers(new String[]{"/css/**", "/js/**"}).permitAll()
					.requestMatchers("/api/**").permitAll()
					.anyRequest().authenticated()
				)
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()
				)
				.logout((logout) -> logout.permitAll())
				;

		return http.build();
	}
}