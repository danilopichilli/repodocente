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

    public List<Docente> findDocenti() {
        return docenteRepository.findAll();
    }

    public Docente findDocenteById(Long id) {
        return docenteRepository.findById(id).get();
    }

    public Optional<Docente> updateDocente(Docente docente, Long id) {
        Optional<Docente> foundDocente = docenteRepository.findById(id);
        if (foundDocente.isEmpty()) {
            return Optional.empty();
        }
        foundDocente.get().setNome(docente.getNome());
        foundDocente.get().setCognome(docente.getCognome());
        docenteRepository.save(foundDocente.get());
        return foundDocente;
    }

    public Optional<Docente> deleteDocente(Long id) {
        Optional<Docente> foundDocente = docenteRepository.findById(id);
        if(foundDocente.isEmpty()) {
            return Optional.empty();
        }else{
            docenteRepository.deleteById(id);
           return foundDocente;
        }
    }

    public List<DocenteDTO> getListaDocenteDto() {
        return docenteConverter.convertEntityToDto(findDocenti());
    }

    public List<Docente> findByNome(String nome) {
        return docenteRepository.findByNome(nome);
    }

    public Docente findByNomeAndCognome(String nome, String cognome) {
        List<Docente> docenteList = docenteRepository.findAll();
        for(Docente docente : docenteList) {
            if(docente.getNome().equals(nome) && docente.getCognome().equals(cognome)) {
                return docenteRepository.findByNomeAndCognome(nome, cognome);
            }
        }
        Docente docente = new Docente();
        docente.setNome(nome);
        docente.setCognome(cognome);
        return docenteRepository.save(docente);
    }


}
