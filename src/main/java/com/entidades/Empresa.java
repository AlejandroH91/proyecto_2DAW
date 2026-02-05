package com.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message="No puedes dejar en blanco los campos")
	@Size(min = 5 , max = 20)
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
			 message = "El nombre solo puede contener letras")
	private String nombre,descripcion;
	
	@ManyToOne
	@JoinColumn(name = "tutor")
	private Profesores tutor;
	
	public Empresa(String nombre, String descripcion, Profesores tutor) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.tutor= tutor;	
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Profesores getTutor() {
		return tutor;
	}

	public void setTutor(Profesores tutor) {
		this.tutor = tutor;
	}
	public String getEmailTutor (Profesores profesor) {
		return profesor.getEmail();
	}
	
}
