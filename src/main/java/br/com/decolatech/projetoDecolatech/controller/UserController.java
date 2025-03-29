package br.com.decolatech.projetoDecolatech.controller;

import br.com.decolatech.projetoDecolatech.domain.models.TaskList;
import br.com.decolatech.projetoDecolatech.domain.models.User;
import br.com.decolatech.projetoDecolatech.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tb_user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/tasklist")
    public ResponseEntity<User> assignTaskList(@PathVariable Long userId, @Valid @RequestBody TaskList taskList) {
        return ResponseEntity.ok(userService.assignTaskList(userId, taskList));
    }

    @PutMapping("/{userId}/tasklist/{taskListId}")
    public ResponseEntity<User> assignExistingTaskList(@PathVariable Long userId, @PathVariable Long taskListId) {
        return ResponseEntity.ok(userService.assignTaskList(userId, taskListId));
    }

    @DeleteMapping("/{userId}/tasklist")
    public ResponseEntity<User> removeTaskList(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.removeTaskList(userId));
    }
}