package com.example.examen_2023.data;

import com.example.examen_2023.logic.Calificacion;
import com.example.examen_2023.logic.Hotel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("hotelRepository")
public class HotelRepository {
    List<Hotel> hoteles;
    public void save(Hotel hotel) {
        hoteles.add(hotel.clone());
    }
    public void addCalificacion(String id, Calificacion calificacion){
        Hotel hotel = hoteles.stream().filter(h -> h.getId().equals(id)).findFirst().get();
        hotel.getCalificaciones().add(calificacion.clone());
    }
    public List<Hotel> findAll() {
        return hoteles;
    }
    public Hotel findById(String id) {
        Hotel hotel = hoteles.stream().filter(h -> h.getId().equals(id)).findFirst().get();
        return hotel.clone();
    }
    public List<Hotel> findTop3(){
        return hoteles.stream().sorted((h1, h2)->Double.compare(h2.calificacion(), h1.calificacion())).
                limit(3).collect(Collectors.toList());
    }
    public List<Hotel> findByNombre(String nombre){
        return hoteles.stream().filter(h -> h.getNombre().contains(nombre)).
                sorted((h1, h2)->Double.compare(h2.calificacion(), h1.calificacion())).
                collect(Collectors.toList());
    }
    public HotelRepository(){
        hoteles= new ArrayList<Hotel>();
        Hotel h;
        h = new Hotel("111.jpg", "Las Hortencias");
        h.getCalificaciones().add(new Calificacion("Juan Bravo", "No me gustó la comida", 1));
        h.getCalificaciones().add(new Calificacion("Felix", "Todo Excelente", 5));
        hoteles.add(h);
        h = new Hotel("222.jpg", "Cocles");
        h.getCalificaciones().add(new Calificacion("Curtis Douglas", "Clean and charm but expensive", 4));
        hoteles.add(h);
    }
}
