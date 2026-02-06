package com.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Practica {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id; 
	private LocalDate fechaInicio, fechaFin;
	
	@ManyToOne
	@JoinColumn(name = "tutor")
	private Profesores tutor;
	
	public Practica(LocalDate fechaInicio, LocalDate fechaFin, Profesores tutor) {
	    this.fechaInicio = fechaInicio;
	    this.fechaFin = fechaFin;
	    this.tutor = tutor;
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
		return tutor;
	}

	public void setTutor(Profesores tutor) {
		this.tutor = tutor;
	}
	
	
	

}
