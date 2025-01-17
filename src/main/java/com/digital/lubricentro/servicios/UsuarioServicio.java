package com.digital.lubricentro.servicios;

import com.digital.lubricentro.entidades.Foto;
import com.digital.lubricentro.entidades.Lubricentro;
import com.digital.lubricentro.entidades.Usuario;
import com.digital.lubricentro.entidades.Vehiculo;
import com.digital.lubricentro.errores.ErrorServicio;
import com.digital.lubricentro.repository.LubricentroRepositorio;
import com.digital.lubricentro.repository.UsuarioRepositorio;
import enumeraciones.Rol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    UsuarioRepositorio uRepo;
    
    @Autowired
    LubricentroRepositorio lRepo;
    
    @Autowired
    FotoServicio fServicio;
    
    public void crearUsuario(String altura, String calle,String clave1,
            String clave2, MultipartFile archivo, String localidad, 
            String mailUsuario, String marca, String nombreUsuario,String slogan,
            String telefono, String sitioWeb) throws ErrorServicio{
       
        Usuario us = new Usuario();
            System.out.println("altura S: "+ altura);
            System.out.println("calle S: "+ calle);
            System.out.println("clave1 S: "+ clave1);
            System.out.println("clave2 S: "+ clave2);
            System.out.println("fotoId S: "+ archivo);
            System.out.println("localidad S: "+ localidad);
            System.out.println("mail usuario S: "+ mailUsuario);
            System.out.println("marca S: "+ marca);
            System.out.println("nombre us S: "+ nombreUsuario);
            System.out.println("slogan S: "+ slogan);
            System.out.println("tel S: "+ telefono);
            System.out.println("web S: "+ sitioWeb);
       
        validar(nombreUsuario,mailUsuario,clave1,clave2);
        
        us.setFechaAlta(new Date());
        us.setNombreUsuario(nombreUsuario.toUpperCase());
        us.setMailUsuario(mailUsuario.toLowerCase());
        us.setMarca(marca);
        us.setSlogan(slogan);
        us.setCalle(calle);
        us.setAltura(altura);
        us.setLocalidad(localidad);
        us.setTelefono(telefono);
        us.setSitioWeb(sitioWeb);
        Foto foto = fServicio.guardarFoto(archivo);
        us.setFoto(foto);
        us.setSitioWeb(sitioWeb);
        us.setEstado(Boolean.TRUE);
        String encriptada = new BCryptPasswordEncoder().encode(clave1);
        us.setClave1(encriptada);
        us.setRol(Rol.ADMIN);
        uRepo.save(us);
        System.out.println("Usuario" + us.getLubris());
        
    }
    public Usuario bucarUsuarioPorMail (String mailUsuario){
        
        return uRepo.buscarPorMail(mailUsuario); 
    }
    
    public String validar(String nombreUsuario,String mailUsuario, String clave1, String clave2) throws ErrorServicio {
        
        
        if(nombreUsuario.isEmpty()){
            throw new ErrorServicio("El nombre del contacto es un campo obligatorio");
        }
        
        List <Usuario> usuariosMail = uRepo.findAll();
        if (mailUsuario.isEmpty()){
            throw new ErrorServicio ("El mail no puede quedar vacio o nulo");
        }
        for (Usuario usuario : usuariosMail) { 
            if (usuario.getMailUsuario().equals(mailUsuario)){     
                String mailEncontrado=mailUsuario;
             throw new ErrorServicio("Ya existe un usuario registrado con este mail (" +mailEncontrado + ") por favor inicie sesión o ingrese otro mial");
         }   
            }
        if (!clave1.equals(clave2) || clave1.length()<=6) {
             throw new ErrorServicio ("Las claves ingresadas no coinciden o tienen menos de 6 caracteres, intente nuevamente");
        }else{
            return clave1;
        }
    }
    
    public Usuario buscarUsuario (String id){
        return uRepo.getOne(id);
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String mailUsuario) throws UsernameNotFoundException {
        Usuario usuario = uRepo.buscarPorMail(mailUsuario);
        
        if (usuario !=null){
            
            List <GrantedAuthority> permisos = new ArrayList<>();
            
            //Creo una lista de permisos! 
            GrantedAuthority p1 = new SimpleGrantedAuthority ("ROLE_"+ usuario.getRol());
            permisos.add(p1);
            
            
            //Esto me permite guardar el OBJETO VENDEDOR LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            
            User user = new User(usuario.getMailUsuario(), usuario.getClave1(), permisos);
            System.out.println(user);
            return user;
        }else{
            return null;
        }
        
    }

    
    
}
