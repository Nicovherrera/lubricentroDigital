package com.digital.lubricentro.entidades;

import enumeraciones.Rol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaAlta;
    private String nombreUsuario;
    private String mailUsuario;
    private String marca;
    private String slogan;
    private String calle;
    private String altura;
    private String localidad;
    private String telefono;
    private String sitioWeb;
    @OneToOne
    private Foto Foto;
    private String clave1;
    private Boolean estado;
    @OneToMany
    private List <Lubricentro> Lubris = new ArrayList<>();
    @ManyToOne
    private Vehiculo vh;
    private Rol rol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMailUsuario() {
        return mailUsuario;
    }

    public void setMailUsuario(String mailUsuario) {
        this.mailUsuario = mailUsuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public Foto getFoto() {
        return Foto;
    }

    public void setFoto(Foto Foto) {
        this.Foto = Foto;
    }

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Lubricentro> getLubris() {
        return Lubris;
    }

    public void setLubris(List<Lubricentro> Lubris) {
        this.Lubris = Lubris;
    }

    public Vehiculo getVh() {
        return vh;
    }

    public void setVh(Vehiculo vh) {
        this.vh = vh;
    }
    

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", fechaAlta=" + fechaAlta + ", nombreUsuario=" + nombreUsuario + ", mailUsuario=" + mailUsuario + ", clave1=" + clave1 + ", estado=" + estado + ", rol=" + rol + '}';
    }
    
    
    
}
