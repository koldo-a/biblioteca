package com.example.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dtos.Favoritos;
import com.example.entidades.Libro;
import com.example.entidades.Usuario;
import com.example.repositorios.LibroRepository;
import com.example.servicios.LibroService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class Controlador {

	private static final String REDIRECT_LISTADO_PRODUCTOS = "redirect:/listado-libros";

	private static final String ATRIBUTO_PRODUCTO = "libro";
	
	private Favoritos favoritos;
	
	public Controlador(LibroService servicio, Favoritos favoritos) {
		this.libroService = servicio;
		this.favoritos = favoritos;
	}
	@Autowired
	private LibroService libroService;
	
    @Autowired
    private LibroRepository libroRepository;
	
    @GetMapping("/libro/{id}")
	public String verDetalleLibro(@PathVariable Long id, Model model) {
	    Libro libro = libroService.obtenerLibroPorId(id);
	    model.addAttribute(ATRIBUTO_PRODUCTO, libro);
	    return "detalle-libro";
	}

    @GetMapping("/listado-libros")
    public String listarLibros(Model model, 
                                @RequestParam(required = false) String nombre, 
                                @RequestParam(required = false) String isbn,
                                @RequestParam(required = false) String autor) {
        Iterable<Libro> libros;
        
        if (nombre != null && !nombre.isEmpty()) {
            // Buscar por nombre
            libros = libroRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (isbn != null && !isbn.isEmpty()) {
            // Buscar por ISBN
            libros = libroRepository.findByIsbn(isbn);
        } else if (autor != null && !autor.isEmpty()) {
            // Buscar por nombre o apellido del autor
        	libros = libroRepository.findByAutorNombreContainingIgnoreCaseOrAutorApellidoContainingIgnoreCase(autor, autor);
        } else {
            // Si no se proporcionan criterios de búsqueda, retornar todos los libros
            libros = libroRepository.findAll();
        }
        
        model.addAttribute("libros", libros);
        model.addAttribute(ATRIBUTO_PRODUCTO, new Libro());
        return "listado-libros";
    }
    
	@PostMapping("/guardarLibro")
	public String guardarLibro(@Valid Libro libro, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("error de entrada");
			return "listado-libros";
		}
		libroService.guardar(libro);

		return REDIRECT_LISTADO_PRODUCTOS;
	}

	@GetMapping("borrarLibro/{id}")
	public String borrarLibro(@PathVariable Long id) {
		libroService.borrar(id);

		return REDIRECT_LISTADO_PRODUCTOS;
	}

	@GetMapping("/")
	public String mostrarPaginaPrincipal(Model modelo) {
		// Lógica para preparar el modelo si es necesario
		modelo.addAttribute("usuario", new Usuario());
		return "index";
	}


	@GetMapping("/editarLibro/{id}")
	public String editarLibro(@PathVariable Long id, Model model) {
		Libro libro = libroService.obtenerLibroPorId(id);
		model.addAttribute(ATRIBUTO_PRODUCTO, libro);
		return "formulario-edicion-libro";
	}


	@PostMapping("/guardarCambiosLibro")
	public String guardarCambiosLibro(@Valid Libro libro, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Manejar los errores de validación si es necesario
			return "formulario-edicion-libro"; // Volver al formulario de edición
		}
		
		libroService.modificar(libro); // Guardar los cambios en el cliente
		
		return REDIRECT_LISTADO_PRODUCTOS; // Redirigir al listado de clientes u otra vista apropiada
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("favoritos/{id}")
	public String favoritos(@PathVariable Long id) {
		var libro = libroService.obtenerLibroPorId(id);
		favoritos.getLibros().put(id, libro);
		return "redirect:/"; // favoritos
	}
}
