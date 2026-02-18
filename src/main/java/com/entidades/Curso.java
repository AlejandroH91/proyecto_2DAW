package com.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Curso{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@NotBlank(message="No puedes dejar en blanco los campos")
	@Size(min = 5 , max = 20)
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede contener letras")
	@Column(unique= true, nullable = false)
	private String nombre;
	
	@ManyToOne
	 private Profesores profesor;
	
	 @Enumerated(EnumType.STRING)//Para que se guarde en String
	  private NombreCurso nombreCurso;
	
	@OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos =new ArrayList <>();
	
	public  Curso(String nombre,List<Alumno> alumnos, Profesores profesor, NombreCurso nombreCurso) {
		this.nombre= nombre;
		this.alumnos=alumnos;
		this.profesor= profesor;
		this.nombreCurso=nombreCurso;
	}
	
	public Curso() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profesores getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesores profesor) {
		this.profesor = profesor;
	}

	public NombreCurso getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(NombreCurso nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	
	
	
	
}
