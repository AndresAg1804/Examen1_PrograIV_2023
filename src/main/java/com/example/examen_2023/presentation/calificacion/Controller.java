package com.example.examen_2023.presentation.calificacion;

import com.example.examen_2023.logic.Calificacion;
import com.example.examen_2023.logic.Hotel;
import com.example.examen_2023.logic.Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@org.springframework.stereotype.Controller("Calificacion")
public class Controller {
    @Autowired
    private Service service;

    @PostMapping("/presentation/Calificacion/show")
    public String show() {
        return "presentation/calificacion/view";
    }

    @PostMapping("/presentation/Calificacion/add")
    public String add(@ModelAttribute(name="hotelEdit", binding =false)Hotel hotelEdit,
                      @ModelAttribute("calificacion") @Valid Calificacion calificacion,
                      BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/presentation/calificacion/view";
        }
        service.addCalificacion(hotelEdit.getId(), calificacion);
        return "redirect:/presentation/Hotel/top3";
    }



}
