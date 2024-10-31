package com.colegio.colegio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.colegio.colegio.dto.ProfesorCreateDTO;
import com.colegio.colegio.dto.ProfesorUpdateDTO;
import com.colegio.colegio.exception.ResourceNotFoundException;
import com.colegio.colegio.model.Profesor;
import com.colegio.colegio.repository.ProfesorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Validated
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    public Profesor getProfesorById(Integer id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con id " + id));
    }

    public Profesor createProfesor(ProfesorCreateDTO profesorCreateDTO) {
        if (profesorRepository.existsByIdentificationNumber(profesorCreateDTO.getIdentificationNumber())) {
            throw new IllegalArgumentException("El número de identificación ya está en uso");
        }
        if (profesorCreateDTO.getIdentificationNumber() == null || profesorCreateDTO.getIdentificationNumber().isEmpty()) {
            throw new ResourceNotFoundException("El número de identificación es obligatorio.");
        }
        if (profesorCreateDTO.getNombre() == null || profesorCreateDTO.getNombre().isEmpty()) {
            throw new ResourceNotFoundException("El nombre es obligatorio.");
        }
        if (profesorCreateDTO.getApellido() == null || profesorCreateDTO.getApellido().isEmpty()) {
            throw new ResourceNotFoundException("El apellido es obligatorio.");
        }
        if (profesorCreateDTO.getEdad() == null || profesorCreateDTO.getEdad().isEmpty()) {
            throw new ResourceNotFoundException("La edad es obligatoria.");
        }
        if (profesorCreateDTO.getUsername() == null || profesorCreateDTO.getUsername().isEmpty()) {
            throw new ResourceNotFoundException("El nombre de usuario es obligatorio.");
        }
        if (profesorCreateDTO.getPassword() == null || profesorCreateDTO.getPassword().isEmpty()) {
            throw new ResourceNotFoundException("La contraseña es obligatoria.");
        }
        
        Profesor nuevoProfesor = new Profesor();
        nuevoProfesor.setIdentificationNumber(profesorCreateDTO.getIdentificationNumber()); 
        nuevoProfesor.setNombre(profesorCreateDTO.getNombre());
        nuevoProfesor.setApellido(profesorCreateDTO.getApellido());
        nuevoProfesor.setEdad(profesorCreateDTO.getEdad());
        nuevoProfesor.setUsername(profesorCreateDTO.getUsername());
        nuevoProfesor.setPassword(passwordEncoder.encode(profesorCreateDTO.getPassword()));
    
        return profesorRepository.save(nuevoProfesor);
    }

    public Profesor updateProfesor(Integer id, ProfesorUpdateDTO profesorUpdateDTO) {
        Profesor profesor = getProfesorById(id);
        if (profesorUpdateDTO.getNombre() != null) {
            profesor.setNombre(profesorUpdateDTO.getNombre());
        }
        if (profesorUpdateDTO.getApellido() != null) {
            profesor.setApellido(profesorUpdateDTO.getApellido());
        }
        if (profesorUpdateDTO.getEdad() != null) {
            profesor.setEdad(profesorUpdateDTO.getEdad());
        }
        if (profesorUpdateDTO.getUsername() != null) {
            profesor.setUsername(profesorUpdateDTO.getUsername());
        }

        return profesorRepository.save(profesor);
    }

    public void deleteProfesor(Integer id) {
        if (!profesorRepository.existsById(id)) {
            throw new EntityNotFoundException("El profesor con ID " + id + " no existe.");
        }
        profesorRepository.deleteById(id);
    }
}
