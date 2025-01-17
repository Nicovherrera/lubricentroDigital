package com.digital.lubricentro.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Vehiculo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombreCliente;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCarga;
    private String mail;
    private String telefono;
    private String marca;
    private String modelo;
    private Integer anio;
    private Boolean gnc;
    private Boolean filtroAceite;
    private Boolean filtroAire;
    private Boolean filtroCombustible;
    private Boolean aceiteMotor;
    private Boolean aceiteCaja;
    private Boolean aceiteDiferencial;
    private Boolean distribucion;
    private Integer kmActuales;
    private Integer kmCambio;
    private String patente;
    private Boolean estado;
    @OneToOne
    private Usuario us;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public Date getFechaCarga() {
        return fechaCarga;
    }
    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Integer getAnio() {
        return anio;
    }
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    public Boolean getGnc() {
        return gnc;
    }
    public void setGnc(Boolean gnc) {
        this.gnc = gnc;
    }
    public Boolean getFiltroAceite() {
        return filtroAceite;
    }
    public void setFiltroAceite(Boolean filtroAceite) {
        this.filtroAceite = filtroAceite;
    }
    public Boolean getFiltroAire() {
        return filtroAire;
    }
    public void setFiltroAire(Boolean filtroAire) {
        this.filtroAire = filtroAire;
    }
    public Boolean getFiltroCombustible() {
        return filtroCombustible;
    }
    public void setFiltroCombustible(Boolean filtroCombustible) {
        this.filtroCombustible = filtroCombustible;
    }
    public Boolean getAceiteMotor() {
        return aceiteMotor;
    }
    public void setAceiteMotor(Boolean aceiteMotor) {
        this.aceiteMotor = aceiteMotor;
    }
    public Boolean getAceiteCaja() {
        return aceiteCaja;
    }
    public void setAceiteCaja(Boolean aceiteCaja) {
        this.aceiteCaja = aceiteCaja;
    }
    public Boolean getAceiteDiferencial() {
        return aceiteDiferencial;
    }
    public void setAceiteDiferencial(Boolean aceiteDiferencial) {
        this.aceiteDiferencial = aceiteDiferencial;
    }
    public Boolean getDistribucion() {
        return distribucion;
    }
    public void setDistribucion(Boolean distribucion) {
        this.distribucion = distribucion;
    }
    public Integer getKmActuales() {
        return kmActuales;
    }
    public void setKmActuales(Integer kmActuales) {
        this.kmActuales = kmActuales;
    }
    public Integer getKmCambio() {
        return kmCambio;
    }
    public void setKmCambio(Integer kmCambio) {
        this.kmCambio = kmCambio;
    }
    public String getPatente() {
        return patente;
    }
    public void setPatente(String patente) {
        this.patente = patente;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }
    
    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", fechaCarga=" + fechaCarga + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + ", gnc=" + gnc + ", kmActuales=" + kmActuales + ", kmCambio=" + kmCambio + ", patente=" + patente + '}';
    }
    
    
}
