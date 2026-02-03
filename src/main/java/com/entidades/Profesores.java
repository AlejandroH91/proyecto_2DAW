package com.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
public class Profesores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//El generated Value para hacerlo autoincremental.
	private int id;
	@NotBlank(message = "No puedes dejar los campos en blanco")
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",/*Expresión regular para que solo puedan tener letras*/
    message = "El nombre solo puede contener letras")
	private String nombre, apellido1, apellido2;
	@NotBlank(message = "No puedes dejar los campos en blanco")
	@Size(min = 6, max = 40, message="Longitud minima de 6")
	private String pass;
	@NotBlank(message = "El email no puede estar vacío")
	@Column(unique = true)//Para asegurar que no exista dos veces el mismo correo.
	@Email(message = "Debe introducir un email válido Ej: juanita23@gmail.com")
	private String email;
	
	private boolean es_Directiva;//Valor negativo por defecto.
	
	@OneToMany(mappedBy = "tutor")
	private List<Empresa> empresas;
	
	public Profesores (String nombre,String apellido1,String apellido2,String email,String pass, boolean es_Directiva) {
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.email=email;
		this.pass=pass;
		this.es_Directiva=es_Directiva;
	}

	public Profesores () {
		
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String tutor_nombreCompleto() {
		String nombre_completo;
		nombre_completo= this.nombre + " " + this.apellido1 + " "+ this.apellido2;
		return nombre_completo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
