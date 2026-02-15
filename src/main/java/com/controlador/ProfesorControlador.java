package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entidades.Profesores;
import com.servicio.profesores.ProfesoresServicio;

@Controller
@RequestMapping("/profesores")
public class ProfesorControlador {

    @Autowired
    private ProfesoresServicio profesoresServicio;

  
    @GetMapping
    public String listarProfesores(Model model) {
        model.addAttribute("listarProfesores", profesoresServicio.mostrarProfesores());
        boolean esAdmin = true; // ajustar según usuario logueado
        model.addAttribute("esAdmin", esAdmin);
        return "profesores";
    }

   
    @PostMapping("/agregar")
    public String guardarProfesor(@ModelAttribute Profesores profesor) {
        profesoresServicio.agregarProfesor(profesor);
        return "redirect:/profesores";
    }

  
    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam String email, Model model) {
        Profesores profesor = profesoresServicio.findByEmail(email);
        model.addAttribute("profesor", profesor);
        return "editarProfesores"; 
    }

  
    @PostMapping("/actualizar")
    public String actualizarProfesor(@RequestParam String email, @ModelAttribute Profesores profesor) {
        // Buscar el profesor existente por email
        Profesores existe = profesoresServicio.findByEmail(email);
        existe.setNombre(profesor.getNombre());
        existe.setApellido1(profesor.getApellido1());
        existe.setApellido2(profesor.getApellido2());
        existe.setEmail(profesor.getEmail());

        // Guardar cambios
        profesoresServicio.editarProfesor(existe.getId(), existe);

        return "redirect:/profesores";
    }


    @GetMapping("/eliminar")
    public String eliminarProfesor(@RequestParam String email) {
        Profesores profesor = profesoresServicio.findByEmail(email);
        profesoresServicio.eliminarProfesor(profesor.getId());
        return "redirect:/profesores";
    }
}
