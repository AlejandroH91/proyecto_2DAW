package com.servicio.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidades.Empresa;
import com.repository.EmpresaRepository;

@Service
public class EmpresaServicioImpl implements EmpresaServicio{
	/*- The type EmpresaServicioImpl must implement the inherited abstract method EmpresaServicio.editarEmpresa(int, Empresa)
	- The type EmpresaServicioImpl must implement the inherited abstract method EmpresaServicio.mostrarEmpresaPorId(int)*/
	@Autowired //Para instanciar la interfaz Repository
	 private EmpresaRepository repository;
	
    public List<Empresa> mostrarEmpresas() {
        return repository.findAll();
    }

	
	public void agregarEmpresa(Empresa empresa) {
		repository.save(empresa);
		
	}
	
	public void eliminarEmpresa(int id) {
		repository.deleteById(id);;
	}
	
	public Empresa mostrarEmpresaPorId(int id){
		return repository.findById(id).orElse(null);
	}
	
	 public void editarEmpresa(int id, Empresa empresa) {
	        Empresa existe = repository.findById(id).orElse(null);
	        if (existe != null) {
	            existe.setNombre(empresa.getNombre());
	            existe.setDescripcion(empresa.getDescripcion());
	            existe.setNombre_tutor(empresa.getNombre_tutor());
	            existe.setEmail_tutor(empresa.getEmail_tutor());
	            repository.save(existe);
	        }
	    }
	

}
