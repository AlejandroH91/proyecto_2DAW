package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entidades.Curso;

@Controller
@RequestMapping()
public class CursoControlador {
	
	@Autowired
	private Curso curso;
	
	@PostMapping
	public String listarCurso(Model model) {
		
		model.addAttribute("listaCurso", curso.getAlumnos());
		
		return "Clases";
	}
}
