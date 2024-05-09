package com.example.servicios;

import com.example.entidades.Libro;

public interface LibroService {
	Libro obtenerLibroPorId(Long id);
    Iterable<Libro> listarLibros();
    void guardar(Libro libro);
    void borrar(Long id);
}
