package br.com.decolatech.projetoDecolatech.domain.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date CreationDate;
    private boolean arquived;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public TaskList() {
    }

    public TaskList(int id, String title, Date creationDate, boolean arquived, List<Task> tasks) {
        this.id = id;
        this.title = title;
        CreationDate = creationDate;
        this.arquived = arquived;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
