package app.vanillajavaapi;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

import app.vanillajavaapi.handlers.TestHandler;
import app.vanillajavaapi.utils.JsonBuilder;

public class ServidorHttpVanilla {

    private static int port = 8000;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new TestHandler());
        server.start();

        System.out.println("Servidor rodando em localhost:" + port);
    }
}
