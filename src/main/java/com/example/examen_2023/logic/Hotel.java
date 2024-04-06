package com.example.examen_2023.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return calificaciones.stream().mapToDouble(Calificacion::getPuntaje).average().orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id) && Objects.equals(nombre, hotel.nombre) && Objects.equals(calificaciones, hotel.calificaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, calificaciones);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", califaciones=" + calificaciones +
                '}';
    }
}
