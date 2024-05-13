package com.example.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entidades.Prestamo;
import com.example.servicios.PrestamoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class ControladorPrestamo {

	@Autowired
	private PrestamoService prestamoService;

	@GetMapping("/prestamo/{id}")
	public String verDetallePrestamo(@PathVariable Long id, Model model) {
	    Prestamo prestamo = prestamoService.obtenerPrestamoPorId(id);
	    model.addAttribute("prestamo", prestamo);
	    return "detalle-prestamo";
	}

	@PostMapping("/guardarPrestamo")
	public String guardarPrestamo(@Valid Prestamo prestamo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("error de entrada");
			return "listado-prestamos";
		}
		prestamoService.guardar(prestamo);

		return "redirect:/listado-prestamos";
	}

	@GetMapping("borrarPrestamo/{id}")
	public String borrarPrestamo(@PathVariable Long id) {
		prestamoService.borrar(id);

		return "redirect:/listado-prestamos";
	}

	@GetMapping("/listado-prestamos")
	public String listarPrestamos(Model modelo) {
		modelo.addAttribute("prestamos", prestamoService.listarPrestamos());
		modelo.addAttribute("prestamo", new Prestamo());
		return "listado-prestamos";
	}

	@GetMapping("/editarPrestamo/{id}")
	public String editarPrestamo(@PathVariable Long id, Model model) {
		Prestamo prestamo = prestamoService.obtenerPrestamoPorId(id);
		model.addAttribute("prestamo", prestamo);
		return "formulario-edicion-prestamo";
	}

	@PostMapping("/guardarCambiosPrestamo")
	public String guardarCambiosPrestamo(@Valid Prestamo prestamo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Manejar los errores de validación si es necesario
			return "formulario-edicion-prestamo"; // Volver al formulario de edición
		}
		
		prestamoService.modificar(prestamo); // Guardar los cambios en el cliente
		
		return "redirect:/listado-prestamos"; // Redirigir al listado de clientes u otra vista apropiada
	}

}
