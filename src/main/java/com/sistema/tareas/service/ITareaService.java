package com.sistema.tareas.service;

import com.sistema.tareas.model.Tarea;
import java.util.List;

public interface ITareaService {
    public List<Tarea> listarTareas();
    public Tarea buscarTareaPorId(Integer id);
    public void guardarTarea(Tarea tarea);
    public void eliminarTarea(Tarea tarea);
}
