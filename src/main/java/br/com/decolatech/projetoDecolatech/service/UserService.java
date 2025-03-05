package br.com.decolatech.projetoDecolatech.service;

import br.com.decolatech.projetoDecolatech.domain.models.TaskList;
import br.com.decolatech.projetoDecolatech.domain.models.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    
    List<User> findAll();
    
    Object findByEmail(String email);
    
    User create(User userToCreate);
    
    User update(Long id, User user);
    
    void delete(Long id);
    
    User assignTaskList(Long userId, TaskList taskList);
    
    User assignTaskList(Long userId, Long taskListId);
    
    User removeTaskList(Long userId);
}
