package com.colegio.colegio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.colegio.dto.EstudianteCreateDTO;
import com.colegio.colegio.model.Estudiante;
import com.colegio.colegio.service.EstudianteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

import com.colegio.colegio.dto.EstudianteUpdateDTO;



@Validated
@RestController
@RequestMapping("/v1/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping 
    @Operation(summary = "Obtener todos los estudiantes", description = "Retorna una lista con todos los estudiantes registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "estudiantes listados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    private List<Estudiante> estudiantes(){
        return estudianteService.getAllEstudiantes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un profesor por ID", description = "Retorna los detalles de un profesor específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesor encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.getEstudianteById(id);
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo profesor", description = "Crea un nuevo profesor con los datos proporcionados en el cuerpo de la solicitud")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Profesor creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos faltantes"),
        @ApiResponse(responseCode = "409", description = "El número de identificación ya está en uso"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })    
    private ResponseEntity<Estudiante> create(@Valid @RequestBody EstudianteCreateDTO estudianteCreateDTO){
        Estudiante crearEstudiante = estudianteService.createEstudiante(estudianteCreateDTO);
        return new ResponseEntity<>(crearEstudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un profesor", description = "Actualiza los datos de un profesor existente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesor actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Estudiante> update(@PathVariable Integer id, @Valid @RequestBody EstudianteUpdateDTO estudianteUpdateDTO) {
        Estudiante editarEstudiante = estudianteService.update(id, estudianteUpdateDTO);
        return new ResponseEntity<>(editarEstudiante, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un profesor", description = "Elimina un profesor de la base de datos según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesor eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        estudianteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
