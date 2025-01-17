package com.digital.lubricentro.servicios;

import com.digital.lubricentro.entidades.Lubricentro;
import com.digital.lubricentro.errores.ErrorServicio;
import com.digital.lubricentro.repository.LubricentroRepositorio;
import com.digital.lubricentro.repository.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LubricentroServicio {
    
    @Autowired
    private LubricentroRepositorio lubriRepo;
    
    @Autowired
    private UsuarioRepositorio uRepo;
    
    public void crearLubricentro(String marca, String slogan, String calle, String altura,
            String localidad, String telefono, String sitioWeb, String mail, String fotoId) throws ErrorServicio{
        
        Lubricentro lubri = new Lubricentro();
        
        lubri.setMarca(marca.toUpperCase());
        lubri.setSlogan(slogan.toUpperCase());
        lubri.setFecha(new Date());
        lubri.setFoto(fotoId);
        lubri.setCalle(calle.toUpperCase());
        lubri.setAltura(altura);
        lubri.setLocalidad(localidad.toUpperCase());
        lubri.setTelefono(telefono);
        lubri.setSitioWeb(sitioWeb);
        lubri.setEstado(Boolean.TRUE);
        lubri.setMailLubri(mail);
        validar(lubri);    
        lubriRepo.save(lubri);
        System.out.println("Lubri"+ lubri);
    }
    
    public void modificarLubricentro(String id, String marca, String slogan, String mail, String calle, String altura,
            String localidad, String telefono, String sitioWeb, String fotoId){
        
        
        
    }
    public void buscarLubricentro(String mail){
        
       
        
    }
    public void habilitarDeshabilitarLubricentro(){
        
    }
    public void calcularProximoCambio(){
        
    }
    
    public List listarLubricentros(){
        return lubriRepo.findAll();
    }
    public Optional<Lubricentro> buscarLubriPorId(String id){
        return lubriRepo.findById(id);
    }
    
    public void validar(Lubricentro lubri) throws ErrorServicio{
        
        if(lubri.getMarca().isEmpty() || lubri.getMarca() == null){
            throw new ErrorServicio("La marca del Lubricentro no puede quedar vacía");
        }
        if(lubri.getCalle().isEmpty() || lubri.getCalle() == null){
            throw new ErrorServicio("El domicilio no puede quedar vacío");
        }
        if(lubri.getAltura().isEmpty()|| lubri.getAltura() == null) {
            throw new ErrorServicio("El número no puede quedar vacío");
        }
        if(lubri.getLocalidad().isEmpty() || lubri.getLocalidad()== null){
            throw new ErrorServicio("La localidad no puede quedar vacía");
        }
        
    }
    
    
    
}
