package com.servicio.curso;

import java.util.List;

import com.entidades.Curso;


public interface CursoServicio {
	
	  List<Curso> mostrarCursos();
	  Curso mostrarCursosPorNombre(String nombre);
	  void agregarCurso(Curso curso);
	  void editarCurso(int id, Curso curso);
	  void eliminarCurso(int id);
	    
}
