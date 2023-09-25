package com.isil.saturno_1431.controller;

import com.isil.saturno_1431.exception.EstudianteNotFundException;
import com.isil.saturno_1431.model.Estudiante;
import com.isil.saturno_1431.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/crear")
    public String crear(Model model, @ModelAttribute Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";


}

    @DeleteMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable int id) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);
        if (optionalEstudiante.isPresent()) {
            estudianteRepository.delete(optionalEstudiante.get());
            return "redirect:/estudiantes"; // Redirige después de eliminar
        } else {
            throw new EstudianteNotFundException(id);
        }
    }


    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable int id, Model model) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        if (optionalEstudiante.isPresent()) {
            Estudiante estudiante = optionalEstudiante.get();
            model.addAttribute("estudiante", estudiante);
            return "editarEstudiante"; // Reemplaza "editarEstudiante" con el nombre de tu vista de edición
        } else {
            throw new EstudianteNotFundException(id);
        }
    }



}




