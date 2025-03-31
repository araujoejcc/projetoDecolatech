package br.com.decolatech.projetoDecolatech.service.impl;

import br.com.decolatech.projetoDecolatech.domain.models.TaskList;
import br.com.decolatech.projetoDecolatech.domain.models.User;
import br.com.decolatech.projetoDecolatech.domain.repository.UserRepository;
import br.com.decolatech.projetoDecolatech.exceptions.DuplicateResourceException;
import br.com.decolatech.projetoDecolatech.exceptions.ResourceNotFoundException;
import br.com.decolatech.projetoDecolatech.exceptions.UserOperationException;
import br.com.decolatech.projetoDecolatech.service.TaskListService;
import br.com.decolatech.projetoDecolatech.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskListService taskListService;

    public UserServiceImpl(UserRepository userRepository, TaskListService taskListService) {
        this.userRepository = userRepository;
        this.taskListService = taskListService;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Object findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User create(User userToCreate) {
        // Check if ID already exists
        if (userToCreate.getId() != null && userRepository.existsById(userToCreate.getId())) {
            throw new DuplicateResourceException("User", "id", userToCreate.getId());
        }
        
        // Check if email is already in use
        if (userRepository.findByEmail(userToCreate.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User", "email", userToCreate.getEmail());
        }
        
        return userRepository.save(userToCreate);
    }

    @Override
    public User update(Long id, User user) {
        User existingUser = findById(id);
        
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            // Check if new email is already in use by another user
            userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                throw new DuplicateResourceException("User", "email", user.getEmail());
            });
            existingUser.setEmail(user.getEmail());
        }
        
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        // Verify user exists before deletion
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User assignTaskList(Long userId, TaskList taskList) {
        User user = findById(userId);
        
        // Create a new task list if it doesn't have an ID
        if (taskList.getId() == null) {
            taskList = taskListService.create(taskList);
        } else {
            try {
                // Verify that the task list exists
                taskListService.findById(taskList.getId());
            } catch (Exception e) {
                throw new ResourceNotFoundException("TaskList", "id", taskList.getId());
            }
        }
        
        user.setTaskList(taskList);
        return userRepository.save(user);
    }

    @Override
    public User assignTaskList(Long userId, Long taskListId) {
        User user = findById(userId);
        
        TaskList taskList;
        try {
            taskList = taskListService.findById(taskListId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("TaskList", "id", taskListId);
        }
        
        user.setTaskList(taskList);
        return userRepository.save(user);
    }

    @Override
    public User removeTaskList(Long userId) {
        User user = findById(userId);
        
        // Store the task list ID before removing it
        TaskList taskList = user.getTaskList();
        
        if (taskList == null) {
            throw new UserOperationException("User does not have a task list assigned");
        }
        
        // Remove the task list from the user
        user.setTaskList(null);
        user = userRepository.save(user);
        
        // Delete the orphaned task list
        try {
            taskListService.delete(taskList.getId());
        } catch (Exception e) {
            throw new UserOperationException("Failed to delete task list with id: " + taskList.getId());
        }
        
        return user;
    }
}
