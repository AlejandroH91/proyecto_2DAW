package com.servicio.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidades.Curso;
import com.repository.CursoRepository;

@Service
public class CursoServicioImpl implements CursoServicio {

    @Autowired
    private CursoRepository repository;

    @Override
    public List<Curso> mostrarCursos() {
        return repository.findAll();
    }

    @Override
    public Curso mostrarCursosPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public void agregarCurso(Curso curso) {
    	repository.save(curso);
    }

    @Override
    public void editarCurso(int id, Curso curso) {
        Curso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        existente.setNombre(curso.getNombre());
        existente.setProfesor(curso.getProfesor());
        existente.setAlumnos(curso.getAlumnos());

        repository.save(existente);
    }

    @Override
    public void eliminarCurso(int id) {
    	repository.deleteById(id);
    }
    
    @Override
    public Curso mostrarCursoPorId(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
}