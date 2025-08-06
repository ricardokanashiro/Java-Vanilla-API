package app.vanillajavaapi.handlers;

import app.vanillajavaapi.utils.JsonBuilder;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class TestHandler implements HttpHandler {

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
            default:
                sendResponse(
                        exchange,
                        405,
                        JsonBuilder.criarJson("message", "Método não permitido!"),
                        "application/json"
                );
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {

        sendResponse(
                exchange,
                200,
                JsonBuilder.criarJson("message", "GET: Listando dados"),
                "application/json"
        );
    }

    private void handlePost(HttpExchange exchange) throws IOException {

        sendResponse(
                exchange,
                201,
                JsonBuilder.criarJson("message", "POST: Adicionando dados"),
                "application/json"
        );
    }

    private void handlePut(HttpExchange exchange) throws IOException {

        sendResponse(
                exchange,
                200,
                JsonBuilder.criarJson("message", "PUT: Atualizando dados"),
                "application/json"
        );
    }

    private void handleDelete(HttpExchange exchange) throws IOException {

        sendResponse(
                exchange,
                200,
                JsonBuilder.criarJson("message", "DELETE: Deletando dados"),
                "application/json"
        );
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String message, String format) throws IOException {
        byte[] bytes = message.getBytes("UTF-8");
        exchange.getResponseHeaders().set("Content-Type", format + "; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);

        try(OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
