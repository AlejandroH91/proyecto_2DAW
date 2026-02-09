package com.entidades;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Practica {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	
	 	@NotNull
	 	@DateTimeFormat(pattern ="yyyy-MM-dd")
	    private LocalDate fechaInicio, fechaFin;

	    @Column(length = 10000)
	    private String comentarios;

	    @ManyToOne
	    @JoinColumn(name = "alumno_id")
	    private Alumno alumno;

	    @ManyToOne
	    @JoinColumn(name = "empresa_id")
	    private Empresa empresa;

	    @ManyToOne
	    @JoinColumn(name = "profesor_id")
	    private Profesores profesor;
	    
	public Practica(LocalDate fechaInicio, LocalDate fechaFin, Profesores profesor, Alumno alumno, Empresa empresa) {
	    this.fechaInicio = fechaInicio;
	    this.fechaFin = fechaFin;
	    this.profesor = profesor;
	    this.alumno= alumno;
	    this.empresa= empresa;
	}

	
	public Practica () {
		
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Profesores getTutor() {
		return profesor;
	}

	public void setTutor(Profesores tutor) {
		this.profesor = tutor;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public String getComentarios() {
		return comentarios;
	}


	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


	public Profesores getProfesor() {
		return profesor;
	}


	public void setProfesor(Profesores profesor) {
		this.profesor = profesor;
	}
	
	
	
	

}
