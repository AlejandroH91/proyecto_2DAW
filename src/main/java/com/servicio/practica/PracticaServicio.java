package com.servicio.practica;

import java.util.List;

import com.entidades.Alumno;
import com.entidades.Empresa;
import com.entidades.Practica;
import com.entidades.Profesores;

public interface PracticaServicio {
	
    List<Practica> mostrarPracticas();

    void guardarPractica(Practica practica);
    
    Practica buscarPracticaPorId(int id);
    
    void eliminarPracticaPorId(int id);
    
    }
