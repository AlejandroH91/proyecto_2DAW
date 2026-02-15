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
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	System.out.println("LOGIN EMAIL: " + email);
    	
        Profesores profesor = profesoresServicio.findByEmail(email);

        if (profesor == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
            
        }
        System.out.println("PASS BD: " + profesor.getPass());
        return new ProfesoresUserDetails(profesor);
    }
}
