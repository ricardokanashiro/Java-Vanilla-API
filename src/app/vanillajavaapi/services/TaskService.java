package app.vanillajavaapi.services;

import app.vanillajavaapi.entities.Task;
import app.vanillajavaapi.repositories.ITaskRepository;

import java.util.List;

public class TaskService {

    ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public void register(String title) {

        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("O título para a tarefa é inválido!");
        }

        this.taskRepository.register(title);
    }

    public void update(int id, String title, boolean done) {

        Task existingTask = this.taskRepository.findById(id);

        if(existingTask == null) {
            throw new IllegalArgumentException("Task com id " + id + " não existe");
        }

        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Título para tarefa é inválido!");
        }

        this.taskRepository.update(id, title, done);
    }

    public void delete(int id) {

        Task existingTask = this.taskRepository.findById(id);

        if(existingTask == null) {
            throw new IllegalArgumentException("Task com id " + id + " não existe");
        }

        this.taskRepository.delete(id);
    }
}
