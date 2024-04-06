package com.example.examen_2023.presentation.hotel;
import com.example.examen_2023.logic.Calificacion;
import com.example.examen_2023.logic.Hotel;
import com.example.examen_2023.logic.Service;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller("Hotel")
@SessionAttributes({"hoteles","hotelEdit","hotelSearch"})
public class Controller {
    @Autowired
    private Service service;

    @ModelAttribute("hoteles") public Iterable<Hotel> hoteles() {return new ArrayList<Hotel>();}
    @ModelAttribute("hotelSearch") public Hotel hotelSearch() {return new Hotel();}
    @ModelAttribute("hotelEdit") public Hotel hotelEdit() {return new Hotel();}

    @GetMapping("/")
    public String show(HttpSession session, Model model){
        if(session.getAttribute("hoteles")==null){
            session.setAttribute("hoteles", service.findTop3());
            model.addAttribute("hoteles", service.findAll());
            //session.setAttribute("hoteles", service.findTop3());
        }
        return "/presentation/hotel/view";
    }

    @PostMapping("/presentation/hotel/calificar")
    public String calificar(@ModelAttribute("hotelEdit") Hotel hotelEdit, HttpSession httpSession, Model model){
        httpSession.setAttribute("hotelEdit", hotelEdit);
        model.addAttribute("hotelEdit", hotelEdit);
        return "/presentation/calificacion/view";
    }

    @GetMapping("/presentation/Hotel/top3")
    public String top3(HttpSession session,Model model) {
        session.setAttribute("hoteles", service.findTop3());
        session.setAttribute("hotelEdit", new Hotel());
        model.addAttribute("hoteles", service.findTop3());
        model.addAttribute("hotelEdit", new Hotel());
        return "/presentation/hotel/view";
    }

    @PostMapping("/presentation/Hotel/search")
    public String search(@ModelAttribute("hotelSearch") Hotel hotelSearch, Model model, HttpSession session){
        if (hotelSearch.getNombre() == "") {
            model.addAttribute("hoteles", service.findTop3());
            session.setAttribute("hoteles", service.findTop3());
        }
        else{
            model.addAttribute("hoteles", service.findByNombre(hotelSearch.getNombre()));
            session.setAttribute("hoteles", service.findByNombre(hotelSearch.getNombre()));
        }
        model.addAttribute("hotelEdit", new Hotel());
        session.setAttribute("hotelEdit", new Hotel());
        return "/presentation/hotel/view";
    }

}
