package com.entidades;

import jakarta.persistence.Column;
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
	
	@NotBlank(message="No puedes dejar en blanco el campo")
	@Size(min = 5 , max = 20)
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 .,&- ]+$", message = "El nombre solo puede contener letras")
	private String nombre;
	
	@NotBlank(message="No puedes dejar en blanco el campo")
	@Size(min = 3 , max = 20)
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede contener letras")
	private String nombre_tutor;
	
	private String descripcion;
	
	@NotBlank(message = "El email no puede estar vacío")
	@Column(unique = true)
	@Email(message = "Debe introducir un email válido Ej: juanita23@gmail.com")
	private String email_tutor;
	
	public Empresa(String nombre, String descripcion, String nombre_tutor, String email_tutor) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.nombre_tutor= nombre_tutor;	
		this.email_tutor= email_tutor;
	}

	public Empresa() {
		
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

	public String getNombre_tutor() {
		return nombre_tutor;
	}

	public void setNombre_tutor(String nombre_tutor) {
		this.nombre_tutor = nombre_tutor;
	}

	public String getEmail_tutor() {
		return email_tutor;
	}

	public void setEmail_tutor(String email_tutor) {
		this.email_tutor = email_tutor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
}
