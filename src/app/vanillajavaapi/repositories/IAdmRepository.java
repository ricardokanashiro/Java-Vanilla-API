package app.vanillajavaapi.repositories;

import app.vanillajavaapi.entities.Adm;

import java.util.List;

public interface IAdmRepository {
    List<Adm> findAll();
     void register(String name, String email, String password);
}
