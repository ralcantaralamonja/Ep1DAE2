package com.isil.saturno_1431.controller;

import com.isil.saturno_1431.model.Carrera;
import com.isil.saturno_1431.model.Estudiante;
import com.isil.saturno_1431.repository.CarreraRepository;
import com.isil.saturno_1431.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteControler {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @GetMapping("")
    String estudianteindex (Model model){
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantesindex";

    }
}
