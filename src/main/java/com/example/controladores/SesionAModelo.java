package com.example.controladores;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.dtos.Favoritos;

@ControllerAdvice
@Configuration
public class SesionAModelo {
	private Favoritos favoritos;
	
	public SesionAModelo(Favoritos favoritos) {
		this.favoritos = favoritos;
	}
	
	@ModelAttribute
	private void favoritos(Model modelo) {
		modelo.addAttribute(favoritos);
	}

}