package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servicio.profesores.ProfesoresServicio;

@Controller
@RequestMapping("/profesores")
public class ProfesorControlador {

	@Autowired
	private ProfesoresServicio profesores;
	
	@GetMapping
	public String listarProfesores(Model model) {
		model.addAttribute("listarProfesores", profesores.mostrarProfesores());//listaProfesores se llama aquí igual que en la vista.
		return "profesores";
	}
}
