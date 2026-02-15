package com.servicio.profesores;

import java.util.List;
import com.entidades.*;

public interface ProfesoresServicio {
	
	List<Profesores> mostrarProfesores();
	Profesores mostrarProfesorPorId(int id);
	public void agregarProfesor(Profesores profesor);
	public void eliminarProfesor(int id);
	public void editarProfesor(int id,Profesores profesor);
	public Profesores findByEmail(String email);
}
