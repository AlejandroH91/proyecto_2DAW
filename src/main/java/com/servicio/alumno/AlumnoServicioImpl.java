package com.servicio.alumno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidades.Alumno;
import com.repository.AlumnoRepository;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {

    @Autowired
    private AlumnoRepository repository;

    @Override
    public List<Alumno> mostrarAlumnos() {
        return repository.findAll();
    }

    @Override
    public Alumno mostrarAlumnoPorId(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    @Override
    public void agregarAlumno(Alumno alumno) {
        repository.save(alumno);
    }

    @Override
    public void editarAlumno(int id, Alumno alumno) {
        Alumno existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        existente.setNombre(alumno.getNombre());
        existente.setApellido1(alumno.getApellido1());
        existente.setApellido2(alumno.getApellido2());
        existente.setEmail(alumno.getEmail());
        existente.setFecha_nacimiento(alumno.getFecha_nacimiento());
        existente.setCurso(alumno.getCurso());
        repository.save(existente);
    }

    @Override
    public void eliminarAlumno(int id) {
        repository.deleteById(id);
    }
}
