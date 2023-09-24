package com.isil.saturno_1431.controller;

import com.isil.saturno_1431.model.Carrera;
import com.isil.saturno_1431.model.Curso;
import com.isil.saturno_1431.repository.CarreraRepository;
import com.isil.saturno_1431.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
    @Autowired
    private CarreraRepository carreraRepository;
    @GetMapping("")
    String carreraindex (Model model){
        List<Carrera>   carreras = carreraRepository.findAll();
        model.addAttribute("carreras", carreras);
        return "carreraindex";

    }
}
