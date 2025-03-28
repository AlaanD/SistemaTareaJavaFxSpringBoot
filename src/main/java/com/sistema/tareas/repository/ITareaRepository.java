package com.sistema.tareas.repository;

import com.sistema.tareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea, Integer> {
}
