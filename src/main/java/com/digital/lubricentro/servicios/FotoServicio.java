package com.digital.lubricentro.servicios;

import com.digital.lubricentro.entidades.Foto;
import com.digital.lubricentro.errores.ErrorServicio;
import com.digital.lubricentro.repository.FotoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
class FotoServicio {
    @Autowired
    FotoRepositorio fotoRepo;
    
    public Foto guardarFoto (MultipartFile archivo) throws ErrorServicio {
        
        if (archivo != null){
        try {    
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
            
            return fotoRepo.save(foto);
            
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        }
            return null;
    }
 
    
       public Foto actualizarFoto (String idFoto, MultipartFile archivo){

           if (archivo != null){
          
        try {    
                Foto foto = new Foto();
                if (idFoto!=null){
                  
                   Optional <Foto> respuesta=fotoRepo.findById(idFoto);
                   if(respuesta.isPresent()){
                       foto=respuesta.get();
                   }
                }
                    foto.setMime(archivo.getContentType());
                    foto.setNombre(archivo.getName());
                    foto.setContenido(archivo.getBytes());
            
            return fotoRepo.save(foto);
                   
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        }
            return null;

}
    
}
