package com.example.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
