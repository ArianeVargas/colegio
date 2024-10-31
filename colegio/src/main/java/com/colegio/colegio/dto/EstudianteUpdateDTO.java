package com.colegio.colegio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EstudianteUpdateDTO {
    @NotBlank(message="El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Positive(message = "La edad debe ser un número positivo")
    private String edad;
    
    private String titulo;
    private String username;
}
