package app.vanillajavaapi.repositories;
import app.vanillajavaapi.entities.Task;
import java.util.List;

public interface ITaskRepository {
    List<Task> findAll();
    Task findById(int id);
    void register(String title);
    void update(int id, String title, boolean done);
    void delete(int id);
}
