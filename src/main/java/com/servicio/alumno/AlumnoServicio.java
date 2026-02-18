package com.servicio.alumno;

import java.util.List;

import com.entidades.Alumno;

public interface AlumnoServicio {
	   List<Alumno> mostrarAlumnos();
	    Alumno mostrarAlumnoPorId(int id);
	    void agregarAlumno(Alumno alumno);
	    void editarAlumno(int id, Alumno alumno);
	    void eliminarAlumno(int id);

}
