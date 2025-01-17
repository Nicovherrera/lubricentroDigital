package com.digital.lubricentro.repository;

import com.digital.lubricentro.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario , String> {
     @Query("SELECT u FROM Usuario u WHERE u.mailUsuario= :mailUsuario")
    public Usuario buscarPorMail (@Param("mailUsuario") String mailUsuario);
     @Query("SELECT u FROM Usuario u WHERE u.id= :id")
    public Usuario buscarPorId (@Param("id") String id);
    
}

    