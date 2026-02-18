package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entidades.Curso;
import com.servicio.curso.CursoServicio;

@Controller
@RequestMapping("/Clases")
public class CursoControlador {
	
	@Autowired
	private CursoServicio curso;
	
	String nombre;
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
}
