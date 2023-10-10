package com.isil.saturno_1431.controller;


import com.isil.saturno_1431.exception.EstudianteNotFundException;
import com.isil.saturno_1431.model.Estudiante;
import com.isil.saturno_1431.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteControler {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("")
    public String estudianteIndex(Model model) {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantesindex";
    }

    @GetMapping("/nuevo")
    public String nuevoEstudianteForm(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "ingresarEstudiante";
    }

    @PostMapping("/crear")
    public String crearEstudiante(
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute Estudiante estudiante,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "ingresarEstudiante";
        }

        try {
            estudianteRepository.save(estudiante);
            redirectAttributes.addFlashAttribute("exito", true);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            e.printStackTrace();
        }
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable int id, Model model) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        if (optionalEstudiante.isPresent()) {
            Estudiante estudiante = optionalEstudiante.get();
            model.addAttribute("estudiante", estudiante);
            return "editarEstudiante";
        } else {
            throw new EstudianteNotFundException(id);
        }
    }

    @PostMapping("/editar/{id}")
    public String actualizarEstudiante(
            Model model,
            @PathVariable int id,
            @Valid @ModelAttribute Estudiante estudiante,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "editarEstudiante";
        }

        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        if (optionalEstudiante.isPresent()) {
            Estudiante existingEstudiante = optionalEstudiante.get();
            existingEstudiante.setNombres(estudiante.getNombres());
            existingEstudiante.setApellidos(estudiante.getApellidos());
            existingEstudiante.setDni(estudiante.getDni());
            existingEstudiante.setCorreo(estudiante.getCorreo());
            existingEstudiante.setCarrera(estudiante.getCarrera());

            estudianteRepository.save(existingEstudiante);
            return "redirect:/estudiantes";
        } else {
            throw new EstudianteNotFundException(id);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable int id) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);
        if (optionalEstudiante.isPresent()) {
            estudianteRepository.delete(optionalEstudiante.get());
            return "redirect:/estudiantes";
        } else {
            throw new EstudianteNotFundException(id);
        }
    }
}
