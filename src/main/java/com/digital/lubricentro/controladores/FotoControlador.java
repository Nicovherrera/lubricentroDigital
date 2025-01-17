package com.digital.lubricentro.controladores;

import com.digital.lubricentro.entidades.Usuario;
import com.digital.lubricentro.errores.ErrorServicio;
import com.digital.lubricentro.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoControlador {

            @Autowired
	    UsuarioServicio uServicio;
	    
	    @GetMapping("/lubriRegistro/{id}")
	    public ResponseEntity<byte[]> fotoArticulo (@PathVariable String id) throws ErrorServicio{
	        
	        Usuario usuario = uServicio.buscarUsuario(id);
	        if(usuario.getFoto()==null){
	            throw new ErrorServicio("El usuario no tiene una foto asignada");
	        }
	        byte[] foto = usuario.getFoto().getContenido();
	        
	        HttpHeaders headers=new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);
	        
	        return new ResponseEntity<>(foto, headers, HttpStatus.OK);
	    }
	
}
