package app.vanillajavaapi.services;

import app.vanillajavaapi.dtos.AdmResponseDTO;
import app.vanillajavaapi.entities.Adm;
import app.vanillajavaapi.repositories.IAdmRepository;
import app.vanillajavaapi.utils.SimpleMapper;

import java.util.List;

public class AdmService {

    private final IAdmRepository admRepository;

    public AdmService(IAdmRepository admRepository) {
        this.admRepository = admRepository;
    }

    public List<AdmResponseDTO> findAll() {

        List<Adm> adms = admRepository.findAll();

        List<AdmResponseDTO> admsFiltered = SimpleMapper.mapList(
                adms,
                adm -> new AdmResponseDTO(adm.getId(), adm.getName(), adm.getEmail())
        );

        return admsFiltered;
    }

    public AdmResponseDTO findById(String id) {

        try {
            int idFormated = Integer.parseInt(id);
            Adm adm = admRepository.findById(idFormated);

            if(adm == null) {
                throw new IllegalArgumentException("Adm não encontrado para o ID: " + id);
            }

            return new AdmResponseDTO(adm.getId(), adm.getName(), adm.getEmail());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID Inválido: " + id);
        }
    }

    public List<AdmResponseDTO> register(String name, String email, String password) {

        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório!");
        }

        if(email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email faltando ou inválido!");
        }

        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException("Senha inválida ou faltando!");
        }

        admRepository.register(name, email, password);
        return this.findAll();
    }

    public List<AdmResponseDTO> update(int id, String name, String email, String password) {

        Adm existing = admRepository.findById(id);

        if (existing == null) {
            throw new IllegalArgumentException("Administrador com ID " + id + " não encontrado");
        }

        if (name != null && !name.isBlank()) existing.setName(name);
        if (email != null && email.contains("@")) existing.setEmail(email);
        if (password != null && password.length() >= 6) existing.setPassword(password);

        admRepository.update(id, existing.getName(), existing.getEmail(), existing.getPassword());
        return this.findAll();
    }

    public List<AdmResponseDTO> delete(int id) {

        Adm existing = admRepository.findById(id);

        if(existing == null) {
            throw new IllegalArgumentException("Administrador com ID " + id + " não encontrado!");
        }

        this.admRepository.delete(id);
        return this.findAll();
    }
}
