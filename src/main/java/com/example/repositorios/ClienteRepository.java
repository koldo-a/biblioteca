package com.example.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.entidades.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
