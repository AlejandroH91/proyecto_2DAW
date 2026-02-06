package com.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Practica {
	
	private LocalDate fechaInicio, fechaFin;
	
	@ManyToOne
	@JoinColumn(name = "tutor")
	private Profesores tutor;
	

}
