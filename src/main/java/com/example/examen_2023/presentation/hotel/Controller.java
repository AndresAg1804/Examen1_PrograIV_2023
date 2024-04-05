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

import java.util.List;

@org.springframework.stereotype.Controller("Hotel")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("/")
    public String show(HttpSession session) {
        List<Hotel> hotelList= null;
        hotelList=(List<Hotel>) session.getAttribute("LHoteles");
        if(hotelList ==null){
            hotelList = service.findTop3();
        }
        session.setAttribute("LHoteles", hotelList);
        return "presentation/hotel/view";
    }

    @PostMapping("/presentation/Hotel/add")
    public String add(@ModelAttribute(name="hotelEdit", binding =false)Hotel hotelEdit,
                      @ModelAttribute("calificacion") @Valid Calificacion calificacion,
                      BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/presentation/hotel/view";
        }
        service.addCalificacion(hotelEdit.getId(), calificacion);
        return "redirect:/presentation/hoteles/top3";
    }

    @GetMapping("/presentation/Hotel/top3")
    public String top3(HttpSession session) {
        session.setAttribute("hoteles", service.findTop3());
        return "/presentation/hotel/view";
    }

    @PostMapping("/presentation/Hotel/search")
    public String search(@RequestParam("hotelSearch") Hotel hotelSearch, HttpSession session){
        session.setAttribute("hoteles",service.findByNombre(hotelSearch.getNombre()));
        return "presentation/hotel/view";
    }

}
