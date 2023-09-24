package com.isil.saturno_1431.controller;

import com.isil.saturno_1431.model.Curso;
import com.isil.saturno_1431.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository; //DAO-Curso

    //INDEX: CUANDO CARGA LA PAGINA: SELECT * FROM curso
    //GET
    //si se desea devolver una vista(HTLM) se debe devolver un STRING
    @GetMapping("") //localhost:8080/cursos
    String index(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "index"; //retornamos el nombre de la vista o HTLM con el atributo cursos
    }


    //NUEVO: MOSTRAR UN FORMULARIO PARA REGISTRAR UN NUEVO CURSCO
    //GET
    @GetMapping("/nuevo")
    String nuevo(Model model){
        model.addAttribute("curso", new Curso());
        return "nuevo"; ///hacia una VISTA O HTML
    }


    //CREAR: REGISTRAR UN NUEVO CURSO: INSERT INTO curso
    //POST
    @PostMapping("/nuevo") //localhost:8080/cursos/nuevo
    String crear(Model model, Curso curso){
        cursoRepository.save(curso);
        return "redirect:/cursos"; //hacia una RUTA URL
    }

    //ACTUALIZAR UN CURSO: UPDATE curso
    //POST

    //ELIMINAR UN CURSO: DELETE FROM curso
    //POST
}
