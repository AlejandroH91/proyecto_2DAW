package com.servicio.empresa;

import java.util.List;
import com.entidades.Empresa;

public interface EmpresaServicio {

    List<Empresa> mostrarEmpresas();
    Empresa mostrarEmpresaPorId(int id);
    void agregarEmpresa(Empresa empresa);
    void editarEmpresa(int id, Empresa empresa);
    void eliminarEmpresa(int id);
}