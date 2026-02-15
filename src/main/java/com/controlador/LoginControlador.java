package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entidades.Profesores;
import com.servicio.profesores.ProfesoresServicio;

@Controller
public class LoginControlador {

	@Autowired
	 private ProfesoresServicio profesorServicio;
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String validarLogin(@RequestParam String email,@RequestParam String pass, Model model ) {
		
		Profesores profesor = profesorServicio.findByEmail(email);
		
		if (profesor.getEmail() != email && (pass != profesor.getPass() || pass == null)){
			model.addAttribute("Error", "Correo o pass incorrecto");
			return "login";
		}else {
			model.addAttribute("usuario", profesor);
			return "redirect: /profesores";
		}
		
	}
	
}
