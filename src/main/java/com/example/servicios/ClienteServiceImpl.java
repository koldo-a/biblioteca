package com.example.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entidades.Cliente;
import com.example.repositorios.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
    private Iterable<Cliente> clientes = new ArrayList<>();

    @Override
    public Iterable<Cliente> listarClientes() {
    	clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    public void guardar(Cliente cliente) {
		cliente.setId(null);
		clienteRepository.save(cliente);
    }

	@Override
	public void borrar(Long id) {
		clienteRepository.deleteById(id);
	}
	
	@Override
	public void modificar(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente obtenerClientePorId(Long id) {
	    return clienteRepository.findById(id).orElse(null);
	}

	
}
