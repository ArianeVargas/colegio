package com.colegio.colegio.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Estudiante {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="identification_number", unique = true, nullable = false)
    private String identificationNumber; 

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String edad;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @CreatedDate
    @Column(name="created_at", updatable=false)
    private String createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private String updateAt;
}
