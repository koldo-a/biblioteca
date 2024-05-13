package com.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "autores")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull (message="El nombre no puede ser nulo")
	@NotBlank (message="El nombre no puede estar vacío")
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull (message="El apellido no puede ser nulo")
	@NotBlank (message="El apellido no puede estar vacío")
	@Column(name = "apellido")
	private String apellido;
	
	@Email
	@Column(name = "email", unique = true)
	private String email;
	
}
