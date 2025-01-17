package com.digital.lubricentro.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/")
public class ControladorPrincipal {
    
    @GetMapping ("/")
    private String inicio(ModelMap mapa){
        mapa.put("title", "Inicio");
        return "Inicio.html";
    }
    @PreAuthorize("hasAnyRole(ADMIN)")
    @GetMapping ("/tarjetaCliente")
    private String tarjeta(ModelMap mapa){
        mapa.put("title", "Tarjeta");
        return "tarjetaCliente";
    }
    
    @GetMapping ("/iniciarSesion")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
	        model.put("title", "Iniciar Sesion");
        
                if (error != null) {
	            model.put("error", "Usuario o clave incorrectos, intenta nuevamente");
	        }
	        if (logout != null) {
	            model.put("logout", "Ha salido correctamente.");
	        }
        return "iniciarSesion.html";
    }
    
    @GetMapping ("/crearCuenta")
    private String crearCuenta(ModelMap mapa){
        mapa.put("title", "Crear Cuenta");
        return "crearCuenta";
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/lubricentro")
    private String lubricentro (){
        return "/lubricentro";
    }
    
}
