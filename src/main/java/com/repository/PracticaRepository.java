package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entidades.Practica;

@Repository
public interface PracticaRepository extends JpaRepository <Practica, Integer>{
	
	Practica findByAlumnoNombre(String nombre);
	
    Practica findByProfesorNombre(String nombre);

    Practica findByProfesorEmail(String email);
}
