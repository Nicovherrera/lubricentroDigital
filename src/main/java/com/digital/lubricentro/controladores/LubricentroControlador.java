package com.digital.lubricentro.controladores;

import com.digital.lubricentro.entidades.Usuario;
import com.digital.lubricentro.entidades.Vehiculo;
import com.digital.lubricentro.errores.ErrorServicio;
import com.digital.lubricentro.servicios.LubricentroServicio;
import com.digital.lubricentro.servicios.UsuarioServicio;
import com.digital.lubricentro.servicios.VehiculoServicio;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class LubricentroControlador {

    @Autowired
    private LubricentroServicio lServicio;

    @Autowired
    private UsuarioServicio uServicio;

    @Autowired
    private VehiculoServicio vServicio;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/misClientes")
    public String misClientes(ModelMap mapa, HttpSession session, @RequestParam(required = false) 
                           String nombre, @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "3") int size) {
    mapa.put("title", "Mis Clientes");
    Usuario login = (Usuario) session.getAttribute("usuariosession");
    Pageable pageable = PageRequest.of(page, size);
    Page<Vehiculo> clientesPage;
    clientesPage = vServicio.encontrarVehiculoPorIdAndNombre(login.getId(), nombre, pageable);
    int totalPages = clientesPage.getTotalPages();
    mapa.addAttribute("currentPage", page);
    mapa.addAttribute("totalPages", Math.max(totalPages, 1)); // Siempre al menos 1
    mapa.addAttribute("vehiculos", clientesPage);

    return "misClientes";
    }
    @PostMapping("/lubricentro")
    public String crearLubricentro(ModelMap mapa, @RequestParam String altura, @RequestParam String calle,
            @RequestParam String clave1, @RequestParam String clave2, @RequestParam MultipartFile archivo,
            @RequestParam String localidad, @RequestParam String mailUsuario, @RequestParam String marca, @RequestParam String nombreUsuario,
            String slogan, @RequestParam String telefono, String web) throws ErrorServicio {

        try {
          
            uServicio.crearUsuario(altura, calle, clave1, clave2, archivo, localidad, mailUsuario, marca, nombreUsuario,
                    slogan, telefono, web);
            return "iniciarSesion";
        } catch (ErrorServicio e) {
            mapa.addAttribute("error", e.getMessage());
            mapa.addAttribute("marcaR", marca);
            mapa.addAttribute("sloganR", slogan);
            mapa.addAttribute("contactoR", nombreUsuario);
            mapa.addAttribute("mailR", mailUsuario);
            mapa.addAttribute("calleR", calle);
            mapa.addAttribute("alturaR", altura);
            mapa.addAttribute("localidadR", localidad);
            mapa.addAttribute("telefonoR", telefono);
            mapa.addAttribute("webR", web);
            mapa.addAttribute("fotoIdR", archivo);
            mapa.addAttribute("clave1", clave1);
            return "crearCuenta";
        }

    }

    @PostMapping("/buscarCoche")
    public String BuscarCliente(String nombre) {

        return "redirect:/misClientes";
    }
   @PostMapping("/modificar-usuario")
    public String modificarUsuario(ModelMap mapa, @RequestParam String id, @RequestParam String altura, 
        @RequestParam String calle, @RequestParam String clave1, @RequestParam String clave2, 
        @RequestParam MultipartFile archivo, @RequestParam String localidad, 
        @RequestParam String mailUsuario, @RequestParam String marca, @RequestParam String nombreUsuario, 
        @RequestParam String slogan, @RequestParam String telefono, @RequestParam String sitioWeb) {
        System.out.println("nombreUsuarioC: "+ nombreUsuario);
        System.out.println("MarcaC: "+ marca);
        System.out.println("idC: "+ id);
        System.out.println("claveC: "+ clave1);
    try {
        uServicio.modificarUsuario(id, altura, calle, clave1, clave2, archivo, localidad, mailUsuario, marca, 
                nombreUsuario, slogan, telefono, sitioWeb);
        return "redirect:/miLubricentro/"+id;
    } catch (ErrorServicio e) {
        mapa.addAttribute("errorM", e.getMessage());
        mapa.addAttribute("id", id);
        mapa.addAttribute("altura", altura);
        mapa.addAttribute("calle", calle);
        mapa.addAttribute("clave1", clave1);
        mapa.addAttribute("clave2", clave2);
        mapa.addAttribute("localidad", localidad);
        mapa.addAttribute("mailUsuario", mailUsuario);
        mapa.addAttribute("marca", marca);
        mapa.addAttribute("nombreUsuario", nombreUsuario);
        mapa.addAttribute("slogan", slogan);
        mapa.addAttribute("telefono", telefono);
        mapa.addAttribute("sitioWeb", sitioWeb);
        mapa.addAttribute("archivo", archivo);
        return "redirect:/miLubricentro/"+id;
    }
}
}
