package com.servicio.practica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidades.Practica;
import com.repository.PracticaRepository;

@Service
public class PracticaServicioImpl implements PracticaServicio {
	
	@Autowired
	private PracticaRepository repository;
	
	public Practica buscarPorNombreAlumno(String nombre) {
		return repository.findByAlumnoNombre(nombre);
	}
	
	public List <Practica> mostrarPracticas(){
		return repository.findAll();
	}
	
	public Practica buscarAlumnoPorNombre(String nombre) {
	    return repository.findByAlumnoNombre(nombre);
	}
	
	public Practica buscarPorNombreProfesor(String nombre) {
		return repository.findByProfesorNombre(nombre);
		
	}
	
	  @Override
	    public Practica buscarPorEmailProfesor(String email) {
	        return repository.findByProfesorEmail(email);
	    }
}
