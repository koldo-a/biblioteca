package com.example.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entidades.Autor;
import com.example.repositorios.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	AutorRepository autorRepository;

	private Iterable<Autor> autores = new ArrayList<>();

	@Override
	public Iterable<Autor> listarAutores() {
		autores = autorRepository.findAll();
		return autores;
	}

	@Override
	public void guardar(Autor autor) {
		autor.setId(null);
		autorRepository.save(autor);
	}

	@Override
	public void borrar(Long id) {
		autorRepository.deleteById(id);
	}

	@Override
	public void modificar(Autor autor) {
		autorRepository.save(autor);
	}

	@Override
	public Autor obtenerAutorPorId(Long id) {
		return autorRepository.findById(id).orElse(null);
	}

}
