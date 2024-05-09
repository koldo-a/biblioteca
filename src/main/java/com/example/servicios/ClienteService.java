package com.example.servicios;

import com.example.entidades.Cliente;

public interface ClienteService {
    Iterable<Cliente> listarClientes();
    void guardar(Cliente cliente);
    void borrar(Long id);
	void modificar(Cliente cliente);
	Cliente obtenerClientePorId(Long id);
}
