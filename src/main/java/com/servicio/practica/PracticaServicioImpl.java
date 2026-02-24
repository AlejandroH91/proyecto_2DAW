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
		
	public List <Practica> mostrarPracticas(){
		return repository.findAll();
	}
	
	
	 public void guardarPractica(Practica practica) {
		 repository.save(practica);
	 }
	    
	  public  Practica buscarPracticaPorId(int id) {
		   
		   return repository.findById(id).orElse(null);
	 }
	  @Override
	  public void eliminarPracticaPorId(int id) {
	    	repository.deleteById(id);
	 }
	 
}
