package app.vanillajavaapi.utils;

import com.sun.net.httpserver.HttpExchange;

public class UrlHelper {

    public static String extrairParametro(HttpExchange exchange, int posicao) {

        String path = exchange.getRequestURI().getPath();
        String[] pathSplited = path.split("/");

        return pathSplited[posicao];
    }
}
