package com.example.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Prestamo;
import com.example.entidades.Usuario;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    int countByUsuarioAndFechaDevolucionIsNull(Usuario usuario);

	List<Prestamo> findByUsuarioNombre(String nombreUsuario);

}
