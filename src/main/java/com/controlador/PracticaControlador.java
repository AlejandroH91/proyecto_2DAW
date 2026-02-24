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

import com.entidades.Practica;
import com.servicio.alumno.AlumnoServicio;
import com.servicio.empresa.EmpresaServicio;
import com.servicio.practica.PracticaServicio;

@Controller
@RequestMapping("/practicas")
public class PracticaControlador {
	
	@Autowired
    private PracticaServicio practicaServicio;

    @Autowired
    private AlumnoServicio alumnoServicio;

    @Autowired
    private EmpresaServicio empresaServicio;
    
    @GetMapping
    public String listarPracticas(Model model) {
        model.addAttribute("listaPracticas", practicaServicio.mostrarPracticas());
        return "practicas/practicas"; 
    }
    
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("practica", new Practica());
        model.addAttribute("listaAlumnos", alumnoServicio.mostrarAlumnos()); 
        model.addAttribute("listaEmpresas", empresaServicio.mostrarEmpresas());
        
        return "practicas/practicaForm";
    }
    
    @PostMapping("/guardar")
    public String guardarPractica(@ModelAttribute("practica") Practica practica) {
        practicaServicio.guardarPractica(practica);
        return "redirect:/practicas";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam int id) {
        practicaServicio.eliminarPracticaPorId(id);
        return "redirect:/practicas";
    }
    
    @GetMapping("/editar/{id}")
    public String editarPractica(@PathVariable int id, Model model) {
        Practica practica = practicaServicio.buscarPracticaPorId(id);
        if (practica != null) {
            model.addAttribute("practica", practica);
            model.addAttribute("listaAlumnos", alumnoServicio.mostrarAlumnos());
            model.addAttribute("listaEmpresas", empresaServicio.mostrarEmpresas());
            return "practicas/practicaForm";
        }
        
        return "redirect:/practicas";
    }
}
