package com.example.examen_2023.presentation.calificacion;

import com.example.examen_2023.logic.Calificacion;
import com.example.examen_2023.logic.Hotel;
import com.example.examen_2023.logic.Service;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;

@org.springframework.stereotype.Controller("Calificacion")
@SessionAttributes({"calificacion","hotel"})
public class Controller {
    @Autowired
    private Service service;

    @ModelAttribute("calificacion") public Calificacion calificacion() {return new Calificacion();}
    @ModelAttribute("hotel") public Hotel hotel() {return new Hotel();}

    @GetMapping("/presentation/Calificacion/show")
    public String show(@RequestParam("id") String idHotel, HttpSession session, Model model){
        Hotel h=service.findById(idHotel);
        if(h!=null){
            session.setAttribute("hotel", h);
            model.addAttribute("hotel", h);
        }
        if(session.getAttribute("calificacion")==null){
            model.addAttribute("calificacion",new Calificacion());
            session.setAttribute("calificacion",new Calificacion());
        }
        return "/presentation/calificacion/view";
    }

    @PostMapping("/presentation/Calificacion/add")
    public String add(@ModelAttribute(value="hotel", binding =false)Hotel hotel,
                      @ModelAttribute("calificacion") @Valid Calificacion calificacion,
                      BindingResult result, Model model, HttpSession session){
        session.setAttribute("calificacion", calificacion);
        if(result.hasErrors()) {
            return "/presentation/calificacion/view";
        }

        service.addCalificacion(hotel.getId(), calificacion);
        return "redirect:/";
    }



}
