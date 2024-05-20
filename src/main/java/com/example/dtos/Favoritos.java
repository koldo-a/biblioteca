package com.example.dtos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.entidades.Libro;

import lombok.Data;

@Component
@SessionScope
@Data
public class Favoritos {
	private Map<Long, Libro> libros = new HashMap<>();
}
