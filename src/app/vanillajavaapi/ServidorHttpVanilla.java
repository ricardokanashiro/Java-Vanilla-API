package app.vanillajavaapi;

import app.vanillajavaapi.handlers.AdmHandler;
import app.vanillajavaapi.repositories.IAdmRepository;
import app.vanillajavaapi.repositories.InMemoryAdmRepository;
import app.vanillajavaapi.services.AdmService;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ServidorHttpVanilla {

    private static int port = 8000;

    public static void main(String[] args) throws IOException {

        IAdmRepository admRepository = new InMemoryAdmRepository();
        AdmService admService = new AdmService(admRepository);
        AdmHandler admHandler = new AdmHandler(admService);

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/adm", admHandler);

        server.start();

        System.out.println("Servidor rodando em localhost:" + port);
    }
}
