package br.com.decolatech.projetoDecolatech.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must have from 3 to 100 characters")
    private String name;

    @NotBlank(message = "E-mail is mandatory")
    @Email(message = "E-mail format invalid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Passwod must contain at least 6 characters")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private TaskList taskList;

    public User() {
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList todoTaskList) {
        this.taskList = todoTaskList;
    }
}