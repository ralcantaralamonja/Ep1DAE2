package com.isil.saturno_1431.controller;

import com.isil.saturno_1431.exception.EstudianteNotFundException;
import com.isil.saturno_1431.model.Estudiante;
import com.isil.saturno_1431.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String crear(RedirectAttributes redirectAttributes, @ModelAttribute Estudiante estudiante, BindingResult bindingResult) {

        try {
            estudianteRepository.save(estudiante);
            redirectAttributes.addFlashAttribute("exito", true); // Agregar mensaje de éxito
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true); // Agregar mensaje de error
            e.printStackTrace(); // Puedes registrar la excepción si es necesario
        }
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
    @PostMapping("/editar/{id}")
    public String actualizarEstudiante(@ModelAttribute Estudiante estudiante) {
        // Verifica si el estudiante existe en la base de datos
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(estudiante.getId());
        if (optionalEstudiante.isPresent()) {
            // Actualiza los datos del estudiante con los nuevos valores
            Estudiante existingEstudiante = optionalEstudiante.get();
            existingEstudiante.setId(estudiante.getId());
            existingEstudiante.setNombres(estudiante.getNombres());
            existingEstudiante.setApellidos(estudiante.getApellidos());
            existingEstudiante.setDni(estudiante.getDni());
            existingEstudiante.setCorreo(estudiante.getCorreo());
            existingEstudiante.setCarrera(estudiante.getCarrera());

            // Guarda el estudiante actualizado en la base de datos
            estudianteRepository.save(existingEstudiante);
            return "redirect:/estudiantes"; // Redirige después de actualizar
        } else {
            throw new EstudianteNotFundException(estudiante.getId());
        }
    }



}




