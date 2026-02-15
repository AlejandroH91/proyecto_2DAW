package com.servicio.practica;

import java.util.List;

import com.entidades.Alumno;
import com.entidades.Empresa;
import com.entidades.Practica;
import com.entidades.Profesores;

public interface PracticaServicio {
	/*métodos abstractos implementados en Practica servicio impl*/
    List<Practica> mostrarPracticas();

    Practica buscarPorNombreAlumno(String nombre);

    Practica buscarPorNombreProfesor(String nombre);

    Practica buscarPorEmailProfesor(String email);
}
