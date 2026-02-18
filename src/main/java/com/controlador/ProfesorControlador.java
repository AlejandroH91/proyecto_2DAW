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
  
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Profesores profesor = profesoresServicio.mostrarProfesorPorId(id);
        model.addAttribute("profesor", profesor);
        return "editarProfesores";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProfesor(
            @PathVariable("id") int id,
            @ModelAttribute Profesores profesor) {
        
        profesor.setId(id);          
        profesoresServicio.editarProfesor(id, profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/eliminar")
    public String eliminarProfesor(@RequestParam String email) {
        Profesores profesor = profesoresServicio.findByEmail(email);
        profesoresServicio.eliminarProfesor(profesor.getId());
        return "redirect:/profesores";
    }
}
