package app.vanillajavaapi.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class UrlHelper {

    public static List<String> extrairParametro(HttpExchange exchange) {

        String path  = exchange.getRequestURI().getPath();
        String base  = exchange.getHttpContext().getPath();
        String rest  = path.length() >= base.length() ? path.substring(base.length()) : "";

        return Arrays.stream(rest.split("/"))
                .map(s -> URLDecoder.decode(s, StandardCharsets.UTF_8))
                .filter(s -> !s.isBlank())
                .toList();
    }

    public static boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) if (!Character.isDigit(s.charAt(i))) return false;
        return !s.isEmpty();
    }

    public static String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
