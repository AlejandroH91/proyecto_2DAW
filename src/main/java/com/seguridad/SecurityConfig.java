package com.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

    private final ProfesoresUserDetailsService userDetailsService;

    // Inyección directa en el constructor evita problemas de ciclo
    public SecurityConfig(ProfesoresUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /*Password encoder para validar hashes BCrypt, lo he desactivado
     *  porque me daba problemas al recuperar la pass encriptada, lo he dejado en
     *  texto plano para comprobar el login*/ 
   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(userDetailsService);

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    // Configuración del AuthenticationManager para Spring Security 6
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.authenticationProvider(authenticationProvider());
        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider()) 
            /*Permiso para las cabereas según el rol cuando se loguea*/
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                .requestMatchers("/profesores/**").hasAnyRole("PROFESOR", "ADMIN")
                .anyRequest().authenticated()
            )
            /*Para evitar que se guarde la sesión en la caché*/
            .headers(headers -> headers
            	    .cacheControl(cache -> cache.disable()) 
            		)
            	/*Para que coincidan nombre y pass*/
            .formLogin(form -> form
            	    .loginPage("/login")
            	    .usernameParameter("username") 
            	    .passwordParameter("password") 
            	    .defaultSuccessUrl("/profesores", true)
            	    .permitAll()
            	)
            /*Para salir de la aplicación*/
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            );

        return http.build();
    }

}
