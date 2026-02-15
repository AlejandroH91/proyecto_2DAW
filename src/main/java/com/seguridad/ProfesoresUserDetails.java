package com.seguridad;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entidades.Profesores;

public class ProfesoresUserDetails implements UserDetails{

	private Profesores profesor;
	
	 public ProfesoresUserDetails(Profesores profesor) {
	        this.profesor = profesor;
	    }
	 
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	        if (profesor.isEs_Directiva()) {
	            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
	        } else {
	            return Collections.singleton(new SimpleGrantedAuthority("ROLE_PROFESOR"));
	        }
	    }

	    public String getPassword() {
	        return profesor.getPass();
	    }

	    public String getUsername() {
	        return profesor.getEmail(); 
	    }

	    public boolean isAccountNonExpired() {
	    	return true; 
	    }

	    public boolean isAccountNonLocked() {
	    	return true; 
	    }

	    public boolean isCredentialsNonExpired() {
	    	return true; 
	    }

	    public boolean isEnabled() {
	    	return true; 
	    }    
}

