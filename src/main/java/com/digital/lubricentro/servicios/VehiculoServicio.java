package com.digital.lubricentro.servicios;

import com.digital.lubricentro.entidades.Usuario;
import com.digital.lubricentro.entidades.Vehiculo;
import com.digital.lubricentro.errores.ErrorServicio;
import com.digital.lubricentro.repository.UsuarioRepositorio;
import com.digital.lubricentro.repository.VehiculoRepositorio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehiculoServicio {
    
    @Autowired
    VehiculoRepositorio vRepo;
    
    @Autowired
    UsuarioRepositorio uRepo;
    
    public void crearVehiculo (String nombreCleinte, String mail, String telefono, String marca,
            String modelo, Integer anio, Integer kmActual, String patente, String usuarioId) throws ErrorServicio {
        
        Vehiculo vNuevo = new Vehiculo();
        Usuario uNuevo = uRepo.buscarPorId(usuarioId);
        
        validarVehiculo(nombreCleinte, mail, telefono, marca, modelo, anio, patente);

        vNuevo.setFechaCarga(new Date());
        vNuevo.setNombreCliente(nombreCleinte.toUpperCase());
        vNuevo.setMail(mail.toLowerCase());
        vNuevo.setTelefono(telefono);
        vNuevo.setMarca(marca.toUpperCase());
        vNuevo.setModelo(modelo.toUpperCase());
        vNuevo.setAnio(anio);
        vNuevo.setPatente(patente.toUpperCase());
        vNuevo.setEstado(Boolean.TRUE);
        vNuevo.setKmActuales(kmActual);
        if(kmActual!=null){
            vNuevo.setKmCambio(kmActual+10000);
        }
        vNuevo.setUs(uNuevo);
        
        vRepo.save(vNuevo);
    }
    
    public void modificarVehiculo(String id, String nombreCliente, String marca,
            String modelo, Integer anio, String patente, Boolean filtroAceite, Boolean filtroAire,
            Boolean filtroCombustible, Boolean aceiteMotor, Boolean aceiteCaja, Boolean aceiteDiferencial,
            Boolean distribucion, Integer kmActuales, Boolean estado){
        
        Vehiculo vM = vRepo.findById(id).get();
        
        vM.setNombreCliente(nombreCliente.toUpperCase());
        vM.setMarca(marca.toUpperCase());
        vM.setModelo(modelo.toUpperCase());
        vM.setAnio(anio);
        if (vM.getKmActuales()!=null){
            if (kmActuales > vM.getKmCambio()) {
                vM.setFechaCarga(new Date());
            }    
        }else{
            vM.setFechaCarga(new Date());
            }
        
        vM.setPatente(patente.toUpperCase());
        vM.setFiltroAceite(filtroAceite);
        vM.setFiltroAire(filtroAire);
        vM.setFiltroCombustible(filtroCombustible);
        vM.setAceiteMotor(aceiteMotor);
        vM.setAceiteCaja(aceiteCaja);
        vM.setAceiteDiferencial(aceiteDiferencial);
        vM.setDistribucion(distribucion);
        vM.setKmActuales(kmActuales);
        vM.setKmCambio(kmActuales+10000);
        vM.setEstado(estado);
        
        vRepo.save(vM);
    }
    
    public int listarVehiculos (){
        int cantVehicuos = vRepo.findAll().size();
        return cantVehicuos;
    }
    public int contarVehiculosPorId (String id){
        int cantVehicuos = vRepo.buscarPorIdUsuario(id).size();
        return cantVehicuos;
    }
    public Page<Vehiculo> encontrarVehiculoPorIdAndNombre(String id, String nombre, Pageable pageable) 
             {
        if(nombre != null && !nombre.isEmpty()){
            return vRepo.encontrarVehiculoConIdAndNombre(id, "%"+ nombre +"%", pageable);
        }else{
            return vRepo.findByUsuarioId(id, pageable);
        }
        
    }
    public Vehiculo buscarVehiculoPorId (String id){
        return vRepo.buscarPorIdDeVehiculo(id);
    }
    
    
    public void validarVehiculo (String nombreCliente, String mail, String tel, String marca,
            String modelo, Integer anio, String patente) throws ErrorServicio{
        
        if (nombreCliente.isEmpty()){
            throw new ErrorServicio("Un Vehiculo no se puede crear sin el nombre de un cliente");
        }
        if (mail.isEmpty()){
                throw new ErrorServicio("Un Vehiculo no se puede crear sin el mail del cliente");
            }
        if (tel.isEmpty()){
                throw new ErrorServicio("Un Vehiculo no se puede crear sin el telefono del cliente");
            }
        if (marca.isEmpty()){
                throw new ErrorServicio("Un Vehiculo no se puede crear sin la Marca");
            }
        if (modelo.isEmpty()){
                throw new ErrorServicio("Un Vehiculo no se puede crear sin el Modelo del mismo");
            }
        if (anio==0 || anio<1 || anio<1900){
                throw new ErrorServicio("Un Vehiculo no se puede crear sin el año");
            }
        if (patente.isEmpty()){
                throw new ErrorServicio("Un Vehiculo no se puede crear sin la patente del mismo");
            }
        
    }
    
}
