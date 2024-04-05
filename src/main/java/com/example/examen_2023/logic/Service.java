package com.example.examen_2023.logic;

import java.util.List;
import java.util.Optional;
import com.example.examen_2023.data.HotelRepository;
import com.example.examen_2023.logic.Hotel;
import com.example.examen_2023.logic.Calificacion;
import org.springframework.beans.factory.annotation.*;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    private HotelRepository hotelRepository;


    public void addCalificacion(String id, Calificacion calificacion) {
        Hotel hotel = hotelRepository.findById(id);
        if(hotel != null) {
            hotel.getCalificaciones().add(calificacion);
        }
    }

    public List<Hotel> findTop3() {
        return hotelRepository.findTop3();
    }

    public List<Hotel> findByNombre(String nombre) {
        return hotelRepository.findByNombre(nombre);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

}
