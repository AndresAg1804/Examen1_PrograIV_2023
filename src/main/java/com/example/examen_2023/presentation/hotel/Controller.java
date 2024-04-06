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
@SessionAttributes({"hoteles","hotelSearch"})
public class Controller {
    @Autowired
    private Service service;

    @ModelAttribute("hoteles") public Iterable<Hotel> hoteles() {return new ArrayList<Hotel>();}
    @ModelAttribute("hotelSearch") public Hotel hotelSearch() {return new Hotel();}

    @GetMapping(value = {"/presentation/Hotel/show","/"})
    public String show(HttpSession session, Model model){
        if(!hotelSearch().getNombre().isEmpty() || session.getAttribute("hoteles")==null){
            model.addAttribute("hoteles",service.findAll());
            session.setAttribute("hoteles",service.findAll());
        }
        return "/presentation/hotel/view";
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
    public String search(
            @ModelAttribute("hotelSearch") Hotel hotelSearch, Model model){
        model.addAttribute("hoteles",service.findByNombre(hotelSearch.getNombre()));
        return "redirect:/presentation/hotel/show";
    }

}
