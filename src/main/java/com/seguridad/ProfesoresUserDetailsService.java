package com.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entidades.Profesores;
import com.servicio.profesores.ProfesoresServicio;

@Service
public class ProfesoresUserDetailsService implements UserDetailsService {

	  @Autowired
	    private ProfesoresServicio profesoresServicio;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Profesores profesor = profesoresServicio.findByEmail(email);

	        System.out.println("Usuario: " + profesor.getEmail() + " Pass: " + profesor.getPass());
	        if (profesor != null) {
	            System.out.println("Usuario encontrado:");
	            System.out.println("Email: " + profesor.getEmail());
	            System.out.println("Pass (hash): " + profesor.getPass());
	        }

	        if (profesor == null) {
	            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
	        }

	        return new ProfesoresUserDetails(profesor);
	    }
	}