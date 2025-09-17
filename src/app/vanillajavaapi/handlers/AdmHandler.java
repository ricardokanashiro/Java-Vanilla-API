package app.vanillajavaapi.handlers;

import app.vanillajavaapi.dtos.AdmResponseDTO;
import app.vanillajavaapi.services.AdmService;
import app.vanillajavaapi.utils.UrlHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

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
        UrlHelper.sendResponse(200, gson.toJson(adms), exchange, "application/json");
    }

    private void handlePost(HttpExchange exchange) throws IOException {

        String body = UrlHelper.readRequestBody(exchange);

        JsonObject json = JsonParser.parseString(body).getAsJsonObject();
        String name = json.get("name").getAsString();
        String password = json.get("password").getAsString();
        String email = json.get("email").getAsString();

        List<AdmResponseDTO> adms = admService.register(name, email, password);

        UrlHelper.sendResponse(200, gson.toJson(adms), exchange, "application/json");
    }

    private void handlePut(HttpExchange exchange) throws IOException {

        List<String> parametros = UrlHelper.extrairParametro(exchange);

        if(parametros.isEmpty()) {
            String errorJson = gson.toJson(Map.of("error", "Erro ao atualizar adm: id is missing!"));
            UrlHelper.sendResponse(400, errorJson, exchange, "application/json");
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

            UrlHelper.sendResponse(200, gson.toJson(adms), exchange, "application/json");
        }
        catch (IllegalArgumentException e) {
            String errorJson = gson.toJson(Map.of("error", e.getMessage()));
            UrlHelper.sendResponse(400, errorJson, exchange,"application/json");
        }
        catch (Exception e) {
            String errorJson = gson.toJson(Map.of("error", "Erro interno do servidor"));
            UrlHelper.sendResponse(500, errorJson, exchange, "application/json");
        }
    }

    private void handleDelete(HttpExchange exchange) throws IOException {

        List<String> parametros = UrlHelper.extrairParametro(exchange);

        if(parametros.isEmpty()) {
            String errorJson = gson.toJson(Map.of("error", "Erro ao atualizar adm: id is missing!"));
            UrlHelper.sendResponse(400, errorJson, exchange, "application/json");
            return;
        }

        try {
            int providedId = Integer.parseInt(parametros.getFirst());
            List<AdmResponseDTO> adms = this.admService.delete(providedId);

            UrlHelper.sendResponse(200, gson.toJson(adms), exchange, "application/json");
        }
        catch (IllegalArgumentException e) {
            String errorJson = gson.toJson(Map.of("error", e.getMessage()));
            UrlHelper.sendResponse(400, errorJson, exchange,"application/json");
        }
        catch (Exception e) {
            String errorJson = gson.toJson(Map.of("error", "Erro interno do servidor"));
            UrlHelper.sendResponse(500, errorJson, exchange, "application/json");
        }
    }
}
