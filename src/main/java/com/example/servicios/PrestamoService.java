package com.example.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidades.Prestamo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface PrestamoService {
	
    Iterable<Prestamo> listarPrestamos();
    void guardar(Prestamo prestamo);
    void borrar(Long id);
	void modificar(Prestamo prestamo);
	Prestamo obtenerPrestamoPorId(Long id);
}
