package com.example.servicios;

import com.example.entidades.Libro;

import jakarta.validation.Valid;

public interface LibroService {
	Libro obtenerLibroPorId(Long id);
    Iterable<Libro> listarLibros();
    void guardar(Libro libro);
    void borrar(Long id);
	void modificar(@Valid Libro libro);
}
