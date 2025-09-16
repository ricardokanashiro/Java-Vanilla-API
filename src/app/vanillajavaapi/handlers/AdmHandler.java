package app.vanillajavaapi.handlers;

import app.vanillajavaapi.dtos.AdmResponseDTO;
import app.vanillajavaapi.services.AdmService;
import app.vanillajavaapi.utils.UrlHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.google.gson.Gson;

import app.vanillajavaapi.repositories.IAdmRepository;

public class AdmHandler implements HttpHandler {

    private final AdmService admService;
    private static Gson gson = new Gson();

    public AdmHandler(AdmService admService) {
        this.admService = admService;
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

        List<String> params = UrlHelper.extrairParametro(exchange);

        List<AdmResponseDTO> adms = admService.findAll();
        sendResponse(200, gson.toJson(adms), exchange, "application/json");
    }

    private void handlePost(HttpExchange exchange) throws IOException {

        String body = UrlHelper.readRequestBody(exchange);

        JsonObject json = JsonParser.parseString(body).getAsJsonObject();
        String name = json.get("name").getAsString();
        String password = json.get("password").getAsString();
        String email = json.get("email").getAsString();

        List<AdmResponseDTO> adms = admService.register(name, email, password);

        sendResponse(200, gson.toJson(adms), exchange, "application/json");
    }

    private void handlePut(HttpExchange exchange) throws IOException {

        List<String> parametros = UrlHelper.extrairParametro(exchange);

        if(parametros.isEmpty()) {
            sendResponse(400, "Erro ao atualizar adm: id is missing!", exchange, "application/json");
            return;
        }

        try {
            int providedId = Integer.parseInt(parametros.getFirst());
            String body = UrlHelper.readRequestBody(exchange);

            JsonObject json = JsonParser.parseString(body).getAsJsonObject();
            String name = json.get("name").getAsString();
            String password = json.get("password").getAsString();
            String email = json.get("email").getAsString();

            List<AdmResponseDTO> adms = admService.update(providedId, name, email, password);

            sendResponse(200, gson.toJson(adms), exchange, "application/json");
        }
        catch (IllegalArgumentException e) {
            sendResponse(400, e.getMessage(), exchange,"application/json");
        }
        catch (Exception e) {
            sendResponse(500, "Erro interno do servidor", exchange, "application/json");
        }
    }

    private void handleDelete(HttpExchange exchange) throws IOException {

        List<String> parametros = UrlHelper.extrairParametro(exchange);

        if(parametros.isEmpty()) {
            sendResponse(400, "Erro ao atualizar adm: id is missing!", exchange, "application/json");
            return;
        }

        try {
            int providedId = Integer.parseInt(parametros.getFirst());
            List<AdmResponseDTO> adms = this.admService.delete(providedId);

            sendResponse(200, gson.toJson(adms), exchange, "application/json");
        }
        catch (IllegalArgumentException e) {
            sendResponse(400, e.getMessage(), exchange,"application/json");
        }
        catch (Exception e) {
            sendResponse(500, "Erro interno do servidor", exchange, "application/json");
        }
    }

    private void sendResponse(int statusCode, String message, HttpExchange exchange, String format) throws IOException {
        byte[] bytes = message.getBytes("UTF-8");
        exchange.getResponseHeaders().set("Content-Type", format + "; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);

        try(OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
