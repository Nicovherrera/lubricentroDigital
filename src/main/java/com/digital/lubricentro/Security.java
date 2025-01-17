package com.digital.lubricentro;

import com.digital.lubricentro.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class Security extends WebSecurityConfigurerAdapter{
    
    @Autowired 
     public UsuarioServicio usuarioServicio;
    
    @Autowired 
     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
            
            auth
                    .userDetailsService(usuarioServicio)
                    .passwordEncoder(new BCryptPasswordEncoder());
            
}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/img/*",
                        "/**").permitAll()
                .and().
                formLogin()
                .loginPage("/iniciarSesion")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")
                .passwordParameter("clave1")
                .defaultSuccessUrl("/misClientes")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/iniciarSesion")             
                .permitAll().
                and().csrf().disable();
    }
    
}
