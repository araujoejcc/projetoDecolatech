package br.com.decolatech.projetoDecolatech.service;

import br.com.decolatech.projetoDecolatech.domain.models.Task;
import br.com.decolatech.projetoDecolatech.domain.models.TaskList;

import java.util.List;

public interface TaskListService {

    TaskList findById(Long id);
    
    List<TaskList> findAll();
    
    TaskList create(TaskList taskListToCreate);
    
    TaskList update(Long id, TaskList taskList);
    
    void delete(Long id);
    
    TaskList addTask(Long taskListId, Task task);
    
    TaskList removeTask(Long taskListId, Long taskId);
    
    TaskList archiveTaskList(Long id);
    
    TaskList unarchiveTaskList(Long id);
}
