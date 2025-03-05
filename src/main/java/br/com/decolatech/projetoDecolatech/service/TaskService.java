package br.com.decolatech.projetoDecolatech.service;

import br.com.decolatech.projetoDecolatech.domain.models.Task;

import java.util.List;

public interface TaskService {

    Task findById(Long id);
    
    List<Task> findAll();
    
    Task create(Task taskToCreate);
    
    Task update(Long id, Task task);
    
    void delete(Long id);
    
    Task markAsCompleted(Long id);
}
