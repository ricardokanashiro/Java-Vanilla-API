package app.vanillajavaapi.services;

import app.vanillajavaapi.dtos.AdmResponseDTO;
import app.vanillajavaapi.entities.Adm;
import app.vanillajavaapi.repositories.IAdmRepository;
import app.vanillajavaapi.utils.SimpleMapper;

import java.util.List;

public class AdmService {

    private final IAdmRepository admRepository;
    private static SimpleMapper mapper = new SimpleMapper();

    public AdmService(IAdmRepository admRepository) {
        this.admRepository = admRepository;
    }

    public List<AdmResponseDTO> findAll() {

        List<Adm> adms = admRepository.findAll();

        List<AdmResponseDTO> admsFiltered = mapper.mapList(
                adms,
                adm -> new AdmResponseDTO(adm.getId(), adm.getName(), adm.getEmail())
        );

        return admsFiltered;
    }

    public AdmResponseDTO findById(String id) {
        int idFormated = Integer.parseInt(id);
        Adm adm = admRepository.findById(idFormated);

        return new AdmResponseDTO(adm.getId(), adm.getName(), adm.getEmail());
    }

    public List<AdmResponseDTO> register(String name, String email, String password) {
        admRepository.register(name, email, password);
        return this.findAll();
    }

    public List<AdmResponseDTO> update(int id, String name, String email, String password) {
        this.admRepository.update(id, name, email, password);
        return this.findAll();
    }
}
