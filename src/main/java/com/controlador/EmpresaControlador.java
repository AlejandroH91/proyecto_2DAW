package com.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.entidades.Empresa;
import com.servicio.empresa.EmpresaServicio;

@Controller
@RequestMapping("/empresas")
public class EmpresaControlador {

    @Autowired
    private EmpresaServicio empresaServicio;

    @GetMapping
    public String listarEmpresas(Model model) {
        model.addAttribute("listaEmpresas", empresaServicio.mostrarEmpresas());
        return "empresas/empresas"; 
    }

    @GetMapping("/nueva")
    public String formularioNuevaEmpresa(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresas/empresaForm";
    }

    @PostMapping("/guardar")
    public String guardarEmpresa(@ModelAttribute Empresa empresa) {
        empresaServicio.agregarEmpresa(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpresa(@PathVariable int id, Model model) {
        model.addAttribute("empresa", empresaServicio.mostrarEmpresaPorId(id));
        return "empresas/empresaForm";
    }

    @GetMapping("/eliminar")
    public String eliminarEmpresa(@RequestParam int id) {
        empresaServicio.eliminarEmpresa(id);
        return "redirect:/empresas";
    }
}