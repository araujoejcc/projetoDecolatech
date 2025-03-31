package br.com.decolatech.projetoDecolatech.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 100, message = "Title must have from 3 to 100 characters")
    private String title;

    private Date CreationDate;
    private boolean arquived;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public TaskList() {
    }

    public TaskList(Long id, String title, Date creationDate, boolean arquived, List<Task> tasks) {
        this.id = id;
        this.title = title;
        CreationDate = creationDate;
        this.arquived = arquived;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public boolean isArquived() {
        return arquived;
    }

    public void setArquived(boolean arquived) {
        this.arquived = arquived;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}