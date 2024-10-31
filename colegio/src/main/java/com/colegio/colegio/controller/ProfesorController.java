package com.colegio.colegio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.colegio.dto.ProfesorCreateDTO;
import com.colegio.colegio.dto.ProfesorUpdateDTO;
import com.colegio.colegio.model.Profesor;
import com.colegio.colegio.service.ProfesorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RestController
@RequestMapping("/v1/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    @Operation(summary = "Obtener todos los profesores", description = "Retorna una lista con todos los profesores registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesores listados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<Profesor> profesores() {
        return profesorService.getAllProfesores();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un profesor por ID", description = "Retorna los detalles de un profesor específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesor encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Integer id) {
        Profesor profesor = profesorService.getProfesorById(id);
        return new ResponseEntity<>(profesor, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo profesor", description = "Crea un nuevo profesor con los datos proporcionados en el cuerpo de la solicitud")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Profesor creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos faltantes"),
        @ApiResponse(responseCode = "409", description = "El número de identificación ya está en uso"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Profesor> create(@Valid @RequestBody ProfesorCreateDTO profesorCreateDTO) {
        Profesor nuevoProfesor = profesorService.createProfesor(profesorCreateDTO);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un profesor", description = "Actualiza los datos de un profesor existente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesor actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Profesor> update(@PathVariable Integer id, @Valid @RequestBody ProfesorUpdateDTO profesorUpdateDTO) {
        Profesor updatedProfesor = profesorService.updateProfesor(id, profesorUpdateDTO);
        return new ResponseEntity<>(updatedProfesor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un profesor", description = "Elimina un profesor de la base de datos según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesor eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        profesorService.deleteProfesor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
