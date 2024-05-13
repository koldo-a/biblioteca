package com.example.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entidades.Cliente;
import com.example.servicios.ClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class ControladorCliente {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listado-clientes")
	public String listarClientes(Model modelo) {
		modelo.addAttribute("clientes", clienteService.listarClientes());
		modelo.addAttribute("cliente", new Cliente());
		return "listado-clientes";
	}

	@GetMapping("/clientes")
	public String mostrarClientes(Model modelo) {
		modelo.addAttribute("clientes", clienteService.listarClientes());
		modelo.addAttribute("cliente", new Cliente());
		return "clientes";
	}

	@PostMapping("/guardarCliente")
	public String guardarCliente(@Valid Cliente cliente, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("error de entrada");
			return "listado-clientes";
		}

		clienteService.guardar(cliente);

		return "redirect:/listado-clientes";
	}

	@GetMapping("borrarCliente/{id}")
	public String borrarCliente(@PathVariable Long id) {
		clienteService.borrar(id);

		return "redirect:/listado-clientes";
	}

	@GetMapping("/editarCliente/{id}")
	public String editarCliente(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.obtenerClientePorId(id);
		model.addAttribute("cliente", cliente);
		return "formulario-edicion-cliente";
	}
	
	@PostMapping("/guardarCambiosCliente")
	public String guardarCambiosCliente(@Valid Cliente cliente, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Manejar los errores de validación si es necesario
			return "formulario-edicion-cliente"; // Volver al formulario de edición
		}

		clienteService.modificar(cliente); // Guardar los cambios en el cliente

		return "redirect:/listado-clientes"; // Redirigir al listado de clientes u otra vista apropiada
	}
}
