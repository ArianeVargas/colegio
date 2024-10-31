package com.colegio.colegio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.colegio.colegio.dto.EstudianteCreateDTO;
import com.colegio.colegio.dto.EstudianteUpdateDTO;
import com.colegio.colegio.exception.ResourceNotFoundException;
import com.colegio.colegio.model.Estudiante;
import com.colegio.colegio.repository.EstudianteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Estudiante> getAllEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Estudiante getEstudianteById(Integer id){
        return estudianteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado por id " + id));
    }

    public Estudiante createEstudiante(EstudianteCreateDTO estudianteCreateDTO){
        if (estudianteRepository.existsByIdentificationNumber(estudianteCreateDTO.getIdentificationNumber())) {
            throw new IllegalArgumentException();
        }
        if (estudianteCreateDTO.getIdentificationNumber() == null || estudianteCreateDTO.getIdentificationNumber().isEmpty()){
            throw new ResourceNotFoundException("El numero de identificacion es obligatorio");
        }
        if (estudianteCreateDTO.getNombre() == null || estudianteCreateDTO.getNombre().isEmpty()){
            throw new ResourceNotFoundException("El nombre es oblogatorio");
        }
        if (estudianteCreateDTO.getApellido() == null || estudianteCreateDTO.getApellido().isEmpty()){
            throw new ResourceNotFoundException("El apellido es obligatorio");
        }
        if (estudianteCreateDTO.getEdad() == null || estudianteCreateDTO.getEdad().isEmpty()){
            throw new ResourceNotFoundException("La edad es obligatoria");
        }
        if (estudianteCreateDTO.getUsername() == null || estudianteCreateDTO.getUsername().isEmpty()){
            throw new ResourceNotFoundException("El nombre de usuario es obligatorio");
        }
        if (estudianteCreateDTO.getPassword() == null || estudianteCreateDTO.getPassword().isEmpty()){
            throw new ResourceNotFoundException("La contraseña es obligatorio");
        }

        Estudiante nuevoEstudiante = new Estudiante();
        nuevoEstudiante.setIdentificationNumber(estudianteCreateDTO.getIdentificationNumber());
        nuevoEstudiante.setNombre(estudianteCreateDTO.getNombre());
        nuevoEstudiante.setApellido(estudianteCreateDTO.getApellido());
        nuevoEstudiante.setEdad(estudianteCreateDTO.getEdad());
        nuevoEstudiante.setUsername(estudianteCreateDTO.getUsername());
        nuevoEstudiante.setPassword(passwordEncoder.encode(estudianteCreateDTO.getPassword()));
        return estudianteRepository.save(nuevoEstudiante);
    }

    public Estudiante update(Integer id, EstudianteUpdateDTO estudianteUpdateDTO){
        Estudiante estudiante = getEstudianteById(id);
        if(estudiante == null){
            throw new ResourceNotFoundException("Estudiante no encontrado con id " + id);
        }
        if(estudianteUpdateDTO.getNombre() != null){
            estudiante.setNombre(estudianteUpdateDTO.getNombre());
        }
        if(estudianteUpdateDTO.getApellido() != null){
            estudiante.setApellido(estudianteUpdateDTO.getApellido());
        }
        if(estudianteUpdateDTO.getEdad() != null){
            estudiante.setEdad(estudianteUpdateDTO.getEdad());
        }
        if(estudianteUpdateDTO.getUsername() != null){
            estudiante.setUsername(estudianteUpdateDTO.getUsername());
        }
        return estudianteRepository.save(estudiante);
    }

    public void delete(Integer id){
        if(!estudianteRepository.existsById(id)){
            throw new EntityNotFoundException("El profesor con id " + id + " no existe.");
        }
        estudianteRepository.deleteById(id);
    }
}
