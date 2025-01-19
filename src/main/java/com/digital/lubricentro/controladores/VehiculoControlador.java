package com.digital.lubricentro.controladores;

import com.digital.lubricentro.entidades.Vehiculo;
import com.digital.lubricentro.errores.ErrorServicio;
import com.digital.lubricentro.servicios.VehiculoServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class VehiculoControlador {
    
    @Autowired
    private VehiculoServicio vs;
    
    @PostMapping("/vehiculoCreado")
    public String crearVehiculo (ModelMap mapa, @RequestParam String nombreCleinte,@RequestParam String mail,
            @RequestParam String telefono, @RequestParam String marca,@RequestParam String modelo, 
            @RequestParam (defaultValue = "0") Integer anio, Integer kmActual, @RequestParam String patente, @RequestParam String usuarioId) throws ErrorServicio{
        
        try{
            vs.crearVehiculo(nombreCleinte, mail, telefono, marca, modelo, anio, kmActual, patente,usuarioId);
            return "redirect:/misClientes";
        }catch(ErrorServicio e){
            mapa.addAttribute("errorV", e.getMessage());
            mapa.addAttribute("nombreV", nombreCleinte);
            mapa.addAttribute("mailV", mail);
            mapa.addAttribute("telV", telefono);
            mapa.addAttribute("marcaV", marca);
            mapa.addAttribute("modeloV", modelo);
            mapa.addAttribute("anioV", anio);
            mapa.addAttribute("kmActualV", kmActual);
            mapa.addAttribute("patenteV", patente);
            return "misClientes";
        }
    }
    
    @GetMapping("/tarjeta/{id}")
    public String tarjeta (ModelMap mapa, HttpSession session, @PathVariable String id){
                
        Vehiculo vNuevo = vs.buscarVehiculoPorId(id);
        
        mapa.put("clientes", vNuevo);
        
        return "tarjeta";
    }
    @PostMapping("/modificarTarjeta")
    public String modificarTarjeta (ModelMap mapa, HttpSession session, @RequestParam String id, String nombreCliente, String marca,
            String modelo, Integer anio, String patente, Boolean gnc, Boolean filtroAceite, Boolean filtroAire,
            Boolean filtroCombustible, Boolean aceiteMotor, Boolean aceiteCaja, Boolean aceiteDiferencial,
            Boolean distribucion, @RequestParam(defaultValue = "0") Integer kmActuales, Boolean estado){
                
            vs.modificarVehiculo(id, nombreCliente, marca, modelo, anio, patente,
               filtroAceite, filtroAire, filtroCombustible, aceiteMotor, aceiteCaja, aceiteDiferencial, filtroAire, kmActuales, aceiteCaja);
        
        return "redirect:/tarjeta/"+id;
    }
    
    @GetMapping("/tarjetaCliente/{id}")
    public String tarjetaCliente (ModelMap mapa, HttpSession session, @PathVariable String id) throws ErrorServicio{
       
        Vehiculo vCliente = vs.buscarVehiculoPorId(id);
        
        mapa.put("lubri", vCliente.getUs());
        
        mapa.put("clientes", vCliente);
        
        return "tarjetaCliente";
    }
}
