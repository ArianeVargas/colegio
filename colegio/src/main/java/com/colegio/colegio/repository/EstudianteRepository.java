package com.colegio.colegio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegio.colegio.model.Estudiante;
import com.colegio.colegio.model.Profesor;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
    
    boolean existsByIdentificationNumber(String identificationNumber);
    Profesor findByIdentificationNumber(String identificationNumber);
}
