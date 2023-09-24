package com.isil.saturno_1431.repository;

import com.isil.saturno_1431.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //REPOSITORIO DE DATOS  --> agregar desde el extends el Jpa (bd y tipo del dato id)
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
