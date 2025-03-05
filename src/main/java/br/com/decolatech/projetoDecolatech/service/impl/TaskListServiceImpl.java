package br.com.decolatech.projetoDecolatech.service.impl;

import br.com.decolatech.projetoDecolatech.domain.models.Task;
import br.com.decolatech.projetoDecolatech.domain.models.TaskList;
import br.com.decolatech.projetoDecolatech.domain.repository.TaskListRepository;
import br.com.decolatech.projetoDecolatech.exceptions.DuplicateResourceException;
import br.com.decolatech.projetoDecolatech.exceptions.ResourceNotFoundException;
import br.com.decolatech.projetoDecolatech.exceptions.TaskListOperationException;
import br.com.decolatech.projetoDecolatech.service.TaskListService;
import br.com.decolatech.projetoDecolatech.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;
    private final TaskService taskService;

    public TaskListServiceImpl(TaskListRepository taskListRepository, TaskService taskService) {
        this.taskListRepository = taskListRepository;
        this.taskService = taskService;
    }

    @Override
    public TaskList findById(Long id) {
        return taskListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList", "id", id));
    }

    @Override
    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList create(TaskList taskListToCreate) {
        if (taskListToCreate.getId() != null && taskListRepository.existsById(taskListToCreate.getId())) {
            throw new DuplicateResourceException("TaskList", "id", taskListToCreate.getId());
        }
        return taskListRepository.save(taskListToCreate);
    }

    @Override
    public TaskList update(Long id, TaskList taskList) {
        TaskList existingTaskList = findById(id);
        
        if (taskList.getTitle() != null) {
            existingTaskList.setTitle(taskList.getTitle());
        }
        
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void delete(Long id) {
        TaskList taskList = findById(id);
        taskListRepository.delete(taskList);
    }

    @Override
    public TaskList addTask(Long taskListId, Task task) {
        TaskList taskList = findById(taskListId);
        
        // Creating new task
        try {
            Task savedTask = taskService.create(task);
            
            // Initializing tasks list if null
            if (taskList.getTasks() == null) {
                taskList.setTasks(new ArrayList<>());
            }
            
            // Adding task to the list
            taskList.getTasks().add(savedTask);
            
            return taskListRepository.save(taskList);
        } catch (Exception e) {
            throw new TaskListOperationException("Failed to add task to task list: " + e.getMessage());
        }
    }

    @Override
    public TaskList removeTask(Long taskListId, Long taskId) {
        TaskList taskList = findById(taskListId);
        
        // Check if task list has tasks
        if (taskList.getTasks() == null || taskList.getTasks().isEmpty()) {
            throw new TaskListOperationException("Task list has no tasks");
        }
        
        // Check if the task exists in the task list
        boolean taskExists = taskList.getTasks().stream()
                .anyMatch(task -> task.getId().equals(taskId));
                
        if (!taskExists) {
            throw new ResourceNotFoundException("Task", "id", taskId + " in task list " + taskListId);
        }
        
        // Finding and removing task
        taskList.getTasks().removeIf(task -> task.getId().equals(taskId));
        
        try {
            // Deleting task
            taskService.delete(taskId);
            return taskListRepository.save(taskList);
        } catch (Exception e) {
            throw new TaskListOperationException("Failed to remove task from task list: " + e.getMessage());
        }
    }

    @Override
    public TaskList archiveTaskList(Long id) {
        TaskList taskList = findById(id);
        
        if (taskList.isArquived()) {
            throw new TaskListOperationException("Task list is already archived");
        }
        
        taskList.setArquived(true);
        return taskListRepository.save(taskList);
    }

    @Override
    public TaskList unarchiveTaskList(Long id) {
        TaskList taskList = findById(id);
        
        if (!taskList.isArquived()) {
            throw new TaskListOperationException("Task list is not archived");
        }
        
        taskList.setArquived(false);
        return taskListRepository.save(taskList);
    }
}
