package com.example.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entidades.Autor;
import com.example.servicios.AutorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class ControladorAutor {

	@Autowired
	private AutorService autorService;

    @GetMapping("/autor/{id}")
	public String verDetalleAutor(@PathVariable Long id, Model model) {
	    Autor autor = autorService.obtenerAutorPorId(id);
	    model.addAttribute("autor", autor);
	    return "detalle-autor";
	}

	@PostMapping("/guardarAutor")
	public String guardarAutor(@Valid Autor autor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("error de entrada");
			return "listado-autores";
		}
		autorService.guardar(autor);

		return "redirect:/listado-autores";
	}

	@GetMapping("borrarAutor/{id}")
	public String borrarAutor(@PathVariable Long id) {
		autorService.borrar(id);

		return "redirect:/listado-autores";
	}

	@GetMapping("/listado-autores")
	public String listarAutores(Model modelo) {
		modelo.addAttribute("autores", autorService.listarAutores());
		modelo.addAttribute("autor", new Autor());
		return "listado-autores";
	}

	@GetMapping("/editarAutor/{id}")
	public String editarAutor(@PathVariable Long id, Model model) {
		Autor autor = autorService.obtenerAutorPorId(id);
		model.addAttribute("autor", autor);
		return "formulario-edicion-autor";
	}

	@PostMapping("/guardarCambiosAutor")
	public String guardarCambiosAutor(@Valid Autor autor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Manejar los errores de validación si es necesario
			return "formulario-edicion-autor"; // Volver al formulario de edición
		}
		
		autorService.modificar(autor); // Guardar los cambios en el cliente
		
		return "redirect:/listado-autores"; // Redirigir al listado de clientes u otra vista apropiada
	}

}
