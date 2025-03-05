package br.com.decolatech.projetoDecolatech.service.impl;

import br.com.decolatech.projetoDecolatech.domain.models.Task;
import br.com.decolatech.projetoDecolatech.domain.repository.TaskRepository;
import br.com.decolatech.projetoDecolatech.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task create(Task taskToCreate) {
        if (taskToCreate.getId() != null && taskRepository.existsById(taskToCreate.getId())) {
            throw new IllegalArgumentException("This task ID already exists.");
        }
        return taskRepository.save(taskToCreate);
    }

    @Override
    public Task update(Long id, Task task) {
        Task existingTask = findById(id);
        
        if (task.getTitle() != null) {
            existingTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            existingTask.setDescription(task.getDescription());
        }
        if (task.getDueDate() != null) {
            existingTask.setDueDate(task.getDueDate());
        }
        if (task.getPriority() != null) {
            existingTask.setPriority(task.getPriority());
        }
        
        return taskRepository.save(existingTask);
    }

    @Override
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }

    @Override
    public Task markAsCompleted(Long id) {
        Task task = findById(id);
        task.setCompleted(true);
        return taskRepository.save(task);
    }
}
