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
@RequestMapping("/Alumnos")
public class AlumnoControlador {
	
	@Autowired
    private AlumnoServicio alumnoServicio;

    @Autowired
    private CursoServicio cursoServicio;
	
    @GetMapping("/alumno/nuevo")
    public String nuevoAlumno(@RequestParam int cursoId, Model model) {
        Curso c = cursoServicio.mostrarCursoPorId(cursoId);
        Alumno a = new Alumno();
        a.setCurso(c);
        model.addAttribute("alumno", a);
        return "/alumnos/nuevoAlumno";
    }

    @GetMapping("/editar")
    public String editarAlumno(@RequestParam int id, Model model) {
        model.addAttribute("alumno", alumnoServicio.mostrarAlumnoPorId(id));
        return "alumnos/alumnoForm";
    }

  
    @GetMapping("/eliminar")
    public String eliminarAlumno(@RequestParam int id) {
        alumnoServicio.eliminarAlumno(id);
        return "redirect:/Clases";
    }
    
    @PostMapping("/actualizar") 
    public String actualizarAlumno(@ModelAttribute Alumno al) {
        alumnoServicio.editarAlumno(al.getId(), al);
        return "redirect:/Clases"; 
    }
    
    @PostMapping("/guardar") // Coincide con /Alumnos/guardar
    public String guardarAlumno(@ModelAttribute Alumno al) {
        alumnoServicio.agregarAlumno(al);
        return "redirect:/Clases";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("listaCursos", cursoServicio.mostrarCursos()); 
        return "/alumnos/nuevoAlumno";
    }

}
