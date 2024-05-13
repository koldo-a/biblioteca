package com.example.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entidades.Prestamo;
import com.example.repositorios.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository prestamoRepository;

	private Iterable<Prestamo> prestamoes = new ArrayList<>();

	@Override
	public Iterable<Prestamo> listarPrestamos() {
		prestamoes = prestamoRepository.findAll();
		return prestamoes;
	}

//	@Override
//	public void guardar(Prestamo prestamo) {
//		prestamo.setId(null);
//		prestamoRepository.save(prestamo);
//	}

	@Override
	public void guardar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Long id) {
		prestamoRepository.deleteById(id);
	}

	@Override
	public void modificar(Prestamo prestamo) {
		prestamoRepository.save(prestamo);
	}

	@Override
	public Prestamo obtenerPrestamoPorId(Long id) {
		return prestamoRepository.findById(id).orElse(null);
	}
}
