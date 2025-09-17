package app.vanillajavaapi.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.vanillajavaapi.entities.Task;

public class InMemoryTaskRepository implements ITaskRepository {

    private static Map<Integer, Task> tasks = new HashMap<>();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task findById(int id) {
        return tasks.get(id);
    }

    @Override
    public void register(String title) {
        Task newTask = new Task(title);
        tasks.put(newTask.getId(), newTask);
    }

    @Override
    public void update(int id, String title, boolean done) {

        Task foundTask = this.findById(id);

        foundTask.setTitle(title);
        foundTask.setDone(done);
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
    }
}
