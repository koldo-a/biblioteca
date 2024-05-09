package com.example.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.entidades.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long> {

	Autor findByNombre(String nombreAutor);
}