package com.example.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotNull(message = "El libro no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
	
	@NotNull(message = "El usuario no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

	@NotNull(message = "La fecha de prestamo no puede ser nula")
    private LocalDate fechaPrestamo;
    
	@NotNull(message = "La fecha de devolucin no puede ser nula")
	@Future(message = "La fecha de devolución no puede ser anterior a la fecha de prestamo")
    private LocalDate fechaDevolucion;

}
