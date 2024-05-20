package com.example.servicios;

import com.example.dtos.Favoritos;
import com.example.entidades.Libro;

import jakarta.validation.Valid;

public interface LibroService {
	Libro obtenerLibroPorId(Long id);
    Iterable<Libro> listarLibros();
    void guardar(Libro libro);
    void borrar(Long id);
	void modificar(@Valid Libro libro);
	
	
	default Favoritos favoritos() {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}
	
	default Libro agregarFavorito(Long id) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}
	
	default boolean toggleFavorito(Long id) {
		 throw new UnsupportedOperationException("NO IMPLEMENTADO");
	    }
}
