package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entidades.Alumno;
import com.entidades.Curso;
import com.servicio.alumno.AlumnoServicio;
import com.servicio.curso.CursoServicio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/Clases")
public class CursoControlador {
	
	@Autowired
    private CursoServicio cursoServicio;
    
    @Autowired
    private AlumnoServicio alumnoServicio;

    @GetMapping
    public String listarTodo(@RequestParam(value = "cursoId", required = false) Integer cursoId, Model model) {
        model.addAttribute("todosLosCursos", cursoServicio.mostrarCursos());
        
        if (cursoId != null) {
            Curso curso = cursoServicio.mostrarCursoPorId(cursoId);
            if (curso != null) {
                // Usamos la propia lista que tiene el curso dentro
                model.addAttribute("todosLosAlumnos", curso.getAlumnos());
                model.addAttribute("cursoSeleccionado", cursoId);
            }
        } else {
            // Si no hay curso seleccionado, enviamos lista vacía para que no se vea nada
            model.addAttribute("todosLosAlumnos", new ArrayList<Alumno>());
        }
        return "Clases";
    }

    @PostMapping("/importar")
    public String importarAlumnos(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return "redirect:/Clases?error=archivoVacio";
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream(), StandardCharsets.UTF_8))) {
            String linea,fecha_nac,nombreCurso;
             br.readLine(); 
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");
                
                // Verificamos que existan las 6 columnas (0 al 5)
                if (datos.length >= 6) {
                    try {
                        Alumno a = new Alumno();
                        a.setNombre(datos[0].trim());
                        a.setApellido1(datos[1].trim());
                        a.setApellido2(datos[2].trim());
                        a.setEmail(datos[3].trim());
                        fecha_nac = datos[4].trim();
                        a.setFecha_nacimiento(LocalDate.parse(fecha_nac));
                        nombreCurso = datos[5].trim();
                        Curso cursoExistente = cursoServicio.mostrarCursosPorNombre(nombreCurso);
                        
                        if (cursoExistente != null) {
                            a.setCurso(cursoExistente);
                        } 
                        alumnoServicio.agregarAlumno(a);
                        
                    } catch (Exception e) {
                      
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "redirect:/Clases";
    }
   
    @GetMapping("/eliminarCurso")
    public String eliminarCurso(@RequestParam int id) {
        Curso c = cursoServicio.mostrarCursoPorId(id);
        if (c != null && (c.getAlumnos() == null || c.getAlumnos().isEmpty())) {
            cursoServicio.eliminarCurso(id);
        }
        return "redirect:/Clases";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "nuevoCurso"; 
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoServicio.agregarCurso(curso);
        return "redirect:/Clases";
    }

    @GetMapping("/alumno/nuevo")
    public String nuevoAlumno(@RequestParam int cursoId, Model model) {
        Curso c = cursoServicio.mostrarCursoPorId(cursoId);
        Alumno a = new Alumno();
        a.setCurso(c);
        model.addAttribute("alumno", a);
        return "alumnoForm";
    }

    @GetMapping("/alumno/editar")
    public String editarAlumno(@RequestParam int id, Model model) {
        model.addAttribute("alumno", alumnoServicio.mostrarAlumnoPorId(id));
        return "alumnoForm";
    }

    @PostMapping("/alumno/actualizar")
    public String actualizarAlumno(@ModelAttribute Alumno al) {
        alumnoServicio.editarAlumno(al.getId(), al);
        return "redirect:/Clases"; 
    }

    @GetMapping("/alumno/eliminar")
    public String eliminarAlumno(@RequestParam int id) {
        alumnoServicio.eliminarAlumno(id);
        return "redirect:/Clases";
    }
}