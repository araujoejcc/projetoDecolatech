package br.com.decolatech.projetoDecolatech.controller;

import br.com.decolatech.projetoDecolatech.domain.models.Task;
import br.com.decolatech.projetoDecolatech.domain.models.TaskList;
import br.com.decolatech.projetoDecolatech.service.TaskListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTaskLists() {
        return ResponseEntity.ok(taskListService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Long id) {
        return ResponseEntity.ok(taskListService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList taskList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskListService.create(taskList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Long id, @RequestBody TaskList taskList) {
        return ResponseEntity.ok(taskListService.update(id, taskList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        taskListService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskListId}/tasks")
    public ResponseEntity<TaskList> addTaskToList(@PathVariable Long taskListId, @RequestBody Task task) {
        return ResponseEntity.ok(taskListService.addTask(taskListId, task));
    }

    @DeleteMapping("/{taskListId}/tasks/{taskId}")
    public ResponseEntity<TaskList> removeTaskFromList(@PathVariable Long taskListId, @PathVariable Long taskId) {
        return ResponseEntity.ok(taskListService.removeTask(taskListId, taskId));
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<TaskList> archiveTaskList(@PathVariable Long id) {
        return ResponseEntity.ok(taskListService.archiveTaskList(id));
    }

    @PatchMapping("/{id}/unarchive")
    public ResponseEntity<TaskList> unarchiveTaskList(@PathVariable Long id) {
        return ResponseEntity.ok(taskListService.unarchiveTaskList(id));
    }
}
