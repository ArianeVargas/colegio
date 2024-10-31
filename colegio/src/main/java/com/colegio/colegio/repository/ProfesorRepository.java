package com.colegio.colegio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.colegio.model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer>{

    boolean existsByIdentificationNumber(String identificationNumber);
    Profesor findByIdentificationNumber(String identificationNumber);
}
