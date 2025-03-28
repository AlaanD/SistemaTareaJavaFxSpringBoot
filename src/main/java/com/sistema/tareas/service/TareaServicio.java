package com.sistema.tareas.service;

import com.sistema.tareas.model.Tarea;
import com.sistema.tareas.repository.ITareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TareaServicio implements ITareaService{

    @Autowired
    private ITareaRepository tareaRepository;

    @Override
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea buscarTareaPorId(Integer id) {
        return tareaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        tareaRepository.delete(tarea);
    }
}
