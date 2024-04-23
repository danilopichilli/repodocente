package com.example.docente.service;

import com.example.docente.converter.DocenteConverter;
import com.example.docente.dto.DocenteDTO;
import com.example.docente.entity.Docente;
import com.example.docente.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DocenteConverter docenteConverter;

    public void createDocente(Docente docente) {
        docenteRepository.save(docente);
    }

    public List<Docente> getAllDocentes() {
        return docenteRepository.findAll();
    }

    public Docente getDocenteById(long id) {
        return docenteRepository.findById(id).get();
    }

    public Optional<Docente> updateDocente(Docente docente, long id) {
        Optional<Docente> foundDocente = docenteRepository.findById(id);
        if (foundDocente.isEmpty()) {
            return Optional.empty();
        }
        foundDocente.get().setNome(docente.getNome());
        foundDocente.get().setCognome(docente.getCognome());
        docenteRepository.save(foundDocente.get());
        return foundDocente;
    }

    public void deleteDocente(long id) {
        docenteRepository.deleteById(id);
    }

    public List<DocenteDTO> convert() {
        return docenteConverter.convertEntityToDto(getAllDocentes());
    }

    public List<Docente> findByNome(String nome) {
        return docenteRepository.findByNome(nome);
    }
}
