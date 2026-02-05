package com.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entidades.Profesores;
import com.repository.ProfesoresRepository;

@Service
public class ProfesoresServicioImpl implements ProfesoresServicio{
	
	@Autowired //Para instanciar la interfaz Repository
	 private ProfesoresRepository repository;

	@Override
	public List<Profesores> mostrarProfesores() {
	    return repository.findAll();
	}

	@Override
	public Profesores mostrarProfesorPorId(int id) {
		 return repository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
	}
	
	/*
	 * Ahora con los datos creados y este método 
	 * agregamos las clases a la lista.*/
	@Override
	public void agregarProfesor(Profesores profesor) {
		repository.save(profesor);
		
	}
	
	@Override
	public void editarProfesor(int id, Profesores profesor) {
		Profesores existe= repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Esos datos no corresponden a nigun profesor"));;
	    if (existe != null) {
	        existe.setNombre(profesor.getNombre());
	        existe.setApellido1(profesor.getApellido1());
	        existe.setApellido2(profesor.getApellido2());
	        existe.setEmail(profesor.getEmail());
	        repository.save(existe);
	    }
	}
	
	@Override
	public void eliminarProfesor(int id) {
		repository.deleteById(id);
	}

}
