package com.entidades;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="No puedes dejar en blanco los campos")
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",/*Expresión regular para que solo puedan tener letras*/
    message = "El nombre solo puede contener letras")
	private String nombre, apellido1, apellido2;
	
	@NotBlank(message = "El email no puede estar vacío")
	@Column(unique = true)
	@Email(message = "Debe introducir un email válido Ej: juanita23@gmail.com")
	private String email;
	
	@NotNull
	private LocalDate fecha_nacimiento;
	
	@ManyToOne
    @JoinColumn(name = "curso_id") 
    private Curso curso;
	
	@OneToMany(mappedBy ="alumno")
	private List <Practica>practicas;
	
	public Alumno(String nombre,String apellido1,String apellido2,String email, LocalDate fecha_nacimiento) {
		this.nombre= nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.email=email;
		this.fecha_nacimiento=fecha_nacimiento;
	}
	
	public Alumno() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Practica> getPracticas() {
		return practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}
	
	
	
	
}
