package com.digital.lubricentro.repository;

import com.digital.lubricentro.entidades.Vehiculo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepositorio extends JpaRepository <Vehiculo , String> {
    
     @Query("SELECT v FROM Vehiculo v WHERE v.us.id= :id")
    public List <Vehiculo> buscarPorIdUsuario (@Param("id") String Id);
    
    @Query("SELECT v FROM Vehiculo v WHERE v.us.id = :id ORDER BY v.fechaCarga DESC")
    Page<Vehiculo> findByUsuarioId(@Param("id") String id, Pageable pageable);

    @Query("SELECT v FROM Vehiculo v WHERE v.us.id = :id AND v.nombreCliente LIKE :nombreCliente  ORDER BY v.fechaCarga DESC")
    Page<Vehiculo> encontrarVehiculoConIdAndNombre(@Param("id") String id, @Param("nombreCliente")String nombreCliente, Pageable pageable);
    
    @Query("SELECT v FROM Vehiculo v WHERE v.id= :id")
    public Vehiculo buscarPorIdDeVehiculo (@Param("id") String Id);
    
}
