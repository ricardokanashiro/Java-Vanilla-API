package app.vanillajavaapi.repositories;

import app.vanillajavaapi.entities.Adm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAdmRepository implements IAdmRepository {

    private static Map<Integer, Adm> adms = new HashMap<>();

    public List<Adm> findAll() {
        return new ArrayList<>(adms.values());
    }

    public Adm findById(int id) {

        return adms.get(id);
    }

    public void register(String name, String email, String password) {
        Adm newAdm = new Adm(name, email, password);
        adms.put(newAdm.getId(), newAdm);
    }
}
