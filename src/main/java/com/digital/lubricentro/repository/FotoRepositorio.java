package com.digital.lubricentro.repository;

import com.digital.lubricentro.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository <Foto , String> {
    
}
