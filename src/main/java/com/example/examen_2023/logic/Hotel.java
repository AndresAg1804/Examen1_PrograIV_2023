package com.example.examen_2023.logic;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    String id;
    String nombre;
    List<Calificacion> calificaciones;
    public Hotel(String id, String nombre, List<Calificacion> calificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.calificaciones = calificaciones;
    }
    public Hotel(String id, String nombre) {
        this(id, nombre, new ArrayList<>());
    }
    public Hotel() {
        this("", "", new ArrayList<>());
    }
    public Hotel clone() {
        return new Hotel(id, nombre, new ArrayList<>(calificaciones));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public double calificacion(){
        return calificaciones.stream().mapToInt(Calificacion::getPuntaje).average().orElse(0);
    }
}
