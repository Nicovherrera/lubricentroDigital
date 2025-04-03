package com.digital.lubricentro.controladores;

import com.digital.lubricentro.entidades.Usuario;
import com.digital.lubricentro.servicios.UsuarioServicio;
import com.digital.lubricentro.servicios.VehiculoServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/")
public class ControladorPrincipal {
    
    @Autowired
    private UsuarioServicio us;
    
    @Autowired
    private VehiculoServicio vs;
    
    @GetMapping ("/")
    public String inicio(ModelMap mapa){
        mapa.put("title", "Inicio");
        return "Inicio.html";
    }
    
    @GetMapping ("/tarjetaCliente")
    public String tarjeta(ModelMap mapa, HttpSession session){
        mapa.put("title", "Tarjeta");
         Usuario login = us.buscarUsuario(session.getId());
        mapa.put("miLubri", login);
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
    @GetMapping("/miLubricentro/{id}")
    public String lubricentro (ModelMap mapa, @PathVariable String id){
        Usuario login = us.buscarUsuario(id);
        mapa.put("miLubri", login);
        mapa.put("cantClientes", vs.contarVehiculosPorId(id));
        return "lubricentro";
    }
    
}
