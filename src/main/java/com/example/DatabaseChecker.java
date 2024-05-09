package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entidades.Cliente;
import com.example.entidades.Libro;
import com.example.repositorios.ClienteRepository;
import com.example.repositorios.LibroRepository;

@Component
public class DatabaseChecker implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseChecker.class);

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private LibroRepository productoRepository;

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Comprobando la conexión a la base de datos...");

		// Crear un cliente de ejemplo y guardarlo en la base de datos
//        Cliente cliente = new Cliente();
//        cliente.setNombre("Koldo");
//        cliente.setApellido("Doe");
//        cliente.setEmail("koldo.doe@email.com");
//        clienteRepository.save(cliente);

		// Recuperar todos los clientes de la base de datos e imprimirlos en la consola
		Iterable<Cliente> clientes = clienteRepository.findAll();
		LOGGER.info("Clientes en la base de datos:");
		for (Cliente c : clientes) {
			LOGGER.info(c.toString());
		}

		LOGGER.info("Comprobación de conexión a la base de datos completada!!!.");

		LOGGER.info("Comprobando la conexión a la base de datos...");

		// Recuperar todos los clientes de la base de datos e imprimirlos en la consola
		Iterable<Libro> productos = productoRepository.findAll();
		LOGGER.info("Productos en la base de datos:");
		for (Libro c : productos) {
			LOGGER.info(c.toString());
		}

		LOGGER.info("Comprobación 2 de conexión a la base de datos completada!!!.");
	}
}
