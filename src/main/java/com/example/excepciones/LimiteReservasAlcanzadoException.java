package com.example.excepciones;

public class LimiteReservasAlcanzadoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LimiteReservasAlcanzadoException(String message) {
        super(message);
    }
}
