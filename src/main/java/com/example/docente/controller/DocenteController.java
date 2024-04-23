package com.example.docente.controller;

import com.example.docente.dto.DocenteDTO;
import com.example.docente.entity.Docente;
import com.example.docente.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;


    @PostMapping("/salva")
    public String create(@RequestBody Docente docente){
        if(docente.getNome().isEmpty() || docente.getCognome().isEmpty())
        {
            return "Non si può salvare vuoto!!";
        }
        docenteService.createDocente(docente);
        return "Salvato!";
    }

    @GetMapping("/lista")
    public List<Docente> getAllDocenti(){
        return docenteService.getAllDocentes();
    }

    @GetMapping("/cerca/{id}")
    public Docente findDocenteById(@PathVariable long id){
        return docenteService.findDocenteById(id);
    }

    @PutMapping("/modifica/{id}") //modifica/1
    public String update(@RequestBody Docente docente, @PathVariable long id){
        if(docenteService.updateDocente(docente,id).isPresent()){
            return "Aggiornato!";
        }
        return "Id non trovato!";
    }

    @DeleteMapping("/cancella")
    public String delete(@PathVariable long id){
        if(docenteService.deleteDocente(id).isPresent()){
            return "Eliminato!";
        }
        return "Id non trovato!";
    }

    @GetMapping("/convert")
    public List<DocenteDTO> convert(){
        return docenteService.convert();
    }

    @GetMapping("/findByNome/{nome}")
    public List<Docente> findByNome(@PathVariable String nome){
        return docenteService.findByNome(nome);
    }

    @GetMapping(value = "/getDocente/{id}", produces = "application/json")
    public Docente getDocenteById(@PathVariable long id){
        return docenteService.findDocenteById(id);
    }


}
