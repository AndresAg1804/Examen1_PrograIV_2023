package com.example.examen_2023.presentation.hotel;
import com.example.examen_2023.logic.Calificacion;
import com.example.examen_2023.logic.Hotel;
import com.example.examen_2023.logic.Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@org.springframework.stereotype.Controller("Hotel")
public class Controller {
    @Autowired
    private Service service;

    @PostMapping("/presentation/Hotel/show")
    public String show() {
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
    public String top3(Model model) {
        model.addAttribute("hoteles", service.findTop3());
        return "/presentation/hotel/view";
    }

}
