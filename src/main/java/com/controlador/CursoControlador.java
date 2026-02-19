package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entidades.Alumno;
import com.entidades.Curso;
import com.servicio.alumno.AlumnoServicio;
import com.servicio.curso.CursoServicio;

@Controller
@RequestMapping("/Clases")
public class CursoControlador {
	
	@Autowired
	private CursoServicio curso;
	
	@Autowired
	private AlumnoServicio alumno;
	
	
	/*
	 * Este primer bloque para las acciones de curso
	 * listar, */
	@GetMapping
	public String listarCurso(Model model) {
		
		model.addAttribute("listaCurso", curso.mostrarCursos());
		
		return "Clases";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormularioCurso(Model model) {
	    model.addAttribute("curso", new Curso());
	    return "nuevoCurso"; 
	}

	@GetMapping("/ver")
	public String verCurso(@RequestParam String nombre, Model model) {

	    model.addAttribute("listaCursos", curso.mostrarCursos());

	    Curso cursoSeleccionado = curso.mostrarCursosPorNombre(nombre);
	    model.addAttribute("curso", cursoSeleccionado);

	    return "Clases";
	}
	
	@PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso cursos) {
		curso.agregarCurso(cursos);
        return "redirect:/Clases";
    }

    @PostMapping("/actualizar")
    public String actualizarCurso(@RequestParam int id, @ModelAttribute Curso cursos) {
    	curso.editarCurso(id, cursos);
        return "redirect:/Clases";
    }

    
    @PostMapping("/eliminarCurso")
    public String eliminarCurso(@RequestParam int id) {
        Curso eliminarCurso = curso.mostrarCursoPorId(id);
        if (eliminarCurso.getAlumnos() == null || eliminarCurso.getAlumnos().isEmpty()) {
            curso.eliminarCurso(id);
        } 
        return "redirect:/Clases";
    }
    
    /*
     * Segundo bloque para el CRUD de los alumnos.*/
    
    @PostMapping("/alumno/guardar")
    public String guardarAlumno(@ModelAttribute Alumno alumno1) {
        alumno.agregarAlumno(alumno1);
        return "redirect:/Clases" ;
    }

    @PostMapping("/alumno/actualizar")
    public String actualizarAlumno(@RequestParam int id, @ModelAttribute Alumno alumno1) {
        alumno.editarAlumno(id, alumno1);
        return "redirect:/Clases/ver?nombre=" + alumno1.getCurso().getNombre();
    }

    @GetMapping("/alumno/eliminar")
    public String eliminarAlumno(@RequestParam int id) {
        Alumno alumno1 = alumno.mostrarAlumnoPorId(id);
        String cursoNombre = alumno1.getCurso().getNombre();
        alumno.eliminarAlumno(id);
        return "redirect:/Clases/ver?nombre=" + cursoNombre;
    }

    @GetMapping("/alumno/nuevo")
    public String nuevoAlumno(@RequestParam int cursoId, Model model) {
        Curso curso1 = curso.mostrarCursoPorId(cursoId);
        Alumno alumno1 = new Alumno();
        alumno1.setCurso(curso1);
        model.addAttribute("alumno", alumno1);
        return "alumnoForm";
    }

    @GetMapping("/alumno/editar")
    public String editarAlumno(@RequestParam int id, Model model) {
        Alumno alumno1 = alumno.mostrarAlumnoPorId(id);
        model.addAttribute("alumno", alumno1);
        return "alumnoForm";
    }

}
