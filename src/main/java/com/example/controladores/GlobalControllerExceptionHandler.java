package com.example.controladores;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, Model model) {
        String errorMessage = "Ha ocurrido un error al procesar la solicitud.";
        String detailedError = ex.getMessage(); // Obtener el mensaje de error detallado
        
        // Extraer el email del error detallado
        int startIndex = detailedError.indexOf("'");
        int endIndex = detailedError.indexOf("'", startIndex + 1);
        String email = detailedError.substring(startIndex + 1, endIndex);
        
        // Agregar los datos al modelo para mostrarlos en la vista de error
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("email", email);
        model.addAttribute("detailedError", detailedError);
        return "error";
    }
}
