package com.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@NotBlank
	private String nombre;
	
    @NotNull
    @Size(min = 10, max = 13, message = "El ISBN debe tener entre 10 y 13 dígitos")
    @Pattern(regexp = "\\d{10,13}", message = "El ISBN debe contener solo dígitos")
    private String isbn;
    
	private String descripcion;
	
    // Relación con Autor
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
