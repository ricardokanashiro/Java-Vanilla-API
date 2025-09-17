package app.vanillajavaapi.handlers;

import app.vanillajavaapi.entities.Task;
import app.vanillajavaapi.services.TaskService;
import app.vanillajavaapi.utils.UrlHelper;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class TaskHandler implements HttpHandler {

    private final TaskService taskService;
    private static final Gson gson = new Gson();

    public TaskHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String metodo = exchange.getRequestMethod();

        switch(metodo) {

            case "GET":
                handleGet(exchange);
                break;
            case "POST":
                handlePost(exchange);
                break;
            case "PUT":
                handlePut(exchange);
                break;
            case "DELETE":
                handleDelete(exchange);
                break;
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {

        List<Task> tasks = this.taskService.findAll();
        UrlHelper.sendResponse(200, gson.toJson(tasks), exchange, "application/json");
    }

    private void handlePost(HttpExchange exchange) throws IOException {

    }

    private void handlePut(HttpExchange exchange) {

    }
    private void handleDelete(HttpExchange exchange) {

    }
}
