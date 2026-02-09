package com.servicio.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidades.Curso;
import com.repository.CursoRepository;

@Service
public class CursoServicioImpl implements CursoServicio {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> mostrarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso mostrarCursosPorNombre(String nombre) {
        return cursoRepository.findByNombre(nombre);
    }

    @Override
    public void agregarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public void editarCurso(int id, Curso curso) {
        Curso existente = cursoRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombre(curso.getNombre());
            cursoRepository.save(existente);
        }
    }

    @Override
    public void eliminarCurso(int id) {
        cursoRepository.deleteById(id);
    }
}