package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		model.addAttribute("listaCurso", curso.mostrarCursosPorNombre(nombre ));
		
		return "Clases";
	}
}
