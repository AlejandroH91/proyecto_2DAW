package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entidades.Profesores;

@Repository
public interface ProfesoresRepository extends JpaRepository <Profesores, Integer>{
	

}
