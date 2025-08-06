package app.vanillajavaapi.handlers;

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
                sendResponse(exchange, 405, "Método não permitido!");
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        sendResponse(exchange, 200, "GET: Listando dados");
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        sendResponse(exchange, 201, "POST: Adicionando dados");
    }

    private void handlePut(HttpExchange exchange) throws IOException {
        sendResponse(exchange, 200, "PUT: Atualizando dados");
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        sendResponse(exchange, 200, "DELETE: Deletando dados");
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        byte[] bytes = message.getBytes("UTF-8");
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);

        try(OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
