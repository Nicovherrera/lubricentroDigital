package com.digital.lubricentro.repository;

import com.digital.lubricentro.entidades.Lubricentro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LubricentroRepositorio extends JpaRepository <Lubricentro , String> {
    
    @Query("SELECT l FROM Lubricentro l WHERE l.mailLubri= :mailLubri")
    public List<Lubricentro> buscarPorMail (@Param("mailLubri") String mailLubri);
     @Query("SELECT l FROM Lubricentro l WHERE l.mailLubri= :mailLubri")
    public Lubricentro buscarUnoPorMail (@Param("mailLubri") String mailLubri);
    
}
