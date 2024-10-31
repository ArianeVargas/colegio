package com.colegio.colegio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstudianteCreateDTO {
    @NotBlank(message = "El número de identificación es obligatorio")
    private String identificationNumber;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "La edad es obligatoria")
    private String edad;

    @NotBlank(message = "El usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
