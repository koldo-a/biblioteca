package com.example.servicios;

import org.springframework.stereotype.Service;

import com.example.entidades.Autor;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface AutorService {
    Iterable<Autor> listarAutores();
    void guardar(Autor autor);
    void borrar(Long id);
	void modificar(Autor autor);
	Autor obtenerAutorPorId(Long id);
}
