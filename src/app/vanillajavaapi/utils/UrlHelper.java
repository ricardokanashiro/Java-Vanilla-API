package app.vanillajavaapi.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class UrlHelper {

    public static String extrairParametro(HttpExchange exchange, int posicao) {

        String path = exchange.getRequestURI().getPath();
        String[] pathSplited = path.split("/");

        return pathSplited[posicao];
    }

    public static String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
