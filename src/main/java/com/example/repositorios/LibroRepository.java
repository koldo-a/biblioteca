package com.example.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entidades.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long> {
	List<Libro> findByNombreContainingIgnoreCase(String nombre);
	List<Libro> findByIsbn(String isbn);
	List<Libro> findByAutorNombreContainingIgnoreCase(String autor);
	List<Libro> findByAutorNombreContainingIgnoreCaseOrAutorApellidoContainingIgnoreCase(String nombre, String apellido);


}
