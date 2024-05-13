package com.example.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entidades.Cliente;
import com.example.entidades.Libro;
import com.example.entidades.Usuario;
import com.example.repositorios.ClienteRepository;
import com.example.repositorios.LibroRepository;
import com.example.repositorios.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	LibroRepository libroRepository;

	private Iterable<Libro> libros = new ArrayList<>();
	
    @Override
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }
	@Override
	public Iterable<Libro> listarLibros() {
		libros = libroRepository.findAll();
		return libros;
	}

	@Override
	public void guardar(Libro producto) {
		producto.setId(null);
		libroRepository.save(producto);
	}
	@Override
	public void borrar(Long id) {
		libroRepository.deleteById(id);
	}
//    comentario
	public LibroServiceImpl(UsuarioRepository usuarioRepository, LibroRepository productoRepository,
			ClienteRepository clienteRepository) {
		usuarioRepository.save(Usuario.builder().nombre("Javier").apellido("Lete").email("javier@email.net")
				.password("$2a$12$mof.u/4EIo58hR7On9DnPevyqBC7kb9FHzT.LN/BjF8xOqQVTP1NO").rol("ADMIN").build());
		usuarioRepository.save(Usuario.builder().nombre("Koldo").apellido("Arretxea").email("koldo@email.net")
				.password("$2a$12$NhIMIVI5wNYc97crXmW9rupNsEbeea1BxPu.H0gSDkiDQrhhmXeZO").rol("ADMIN").build());
		usuarioRepository.save(Usuario.builder().nombre("Pepe").apellido("Peponez").email("pepe@email.net")
				.password("$2a$12$Dij9cgV3mXDQYtOo4nvQTOLaUz3URoe7DGjhBrqGa1fEEzkNVhBgq").rol("USER").build());
	}

	@Override
	public void modificar(Libro libro) {
		libroRepository.save(libro);
	}
}