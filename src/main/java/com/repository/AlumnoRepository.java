package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entidades.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository <Alumno, Integer>{

}
