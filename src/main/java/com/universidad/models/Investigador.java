package com.universidad.models; // Paquete que contiene la clase Investigador

import java.util.List; // Importación de la clase List

// Clase que representa a un investigador
public class Investigador {
    private String nombre; 
    private String afiliacion; 
    private String citaciones;
    private String correo; 
    private List<String> intereses; 

    // Constructor para inicializar atributos
    public Investigador(String nombre, String afiliacion, String citaciones, String correo, List<String> intereses) {
        this.nombre = nombre;
        this.afiliacion = afiliacion;
        this.citaciones = citaciones;
        this.correo = correo;
        this.intereses = intereses;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public String getCitaciones() {
        return citaciones;
    }

    public String getCorreo() { 
        return correo;
    }

    public List<String> getIntereses() {
        return intereses;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public void setCitaciones(String citaciones) {
        this.citaciones = citaciones;
    }

    public void setCorreo(String correo) { 
        this.correo = correo;
    }

    public void setIntereses(List<String> intereses) {
        this.intereses = intereses;
    }
}
