package com.example.docente.controller;

import com.example.docente.dto.DocenteDTO;
import com.example.docente.entity.Docente;
import com.example.docente.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return docenteService.findDocenti();
    }

    @GetMapping("/cerca/{id}")
    public Docente findDocenteById(@PathVariable Long id){
        return docenteService.findDocenteById(id);
    }

    @PutMapping("/modifica/{id}") //modifica/1
    public String update(@RequestBody Docente docente, @PathVariable Long id){
        if(docenteService.updateDocente(docente,id).isPresent()){
            return "Aggiornato!";
        }
        return "Id non trovato!";
    }

    @DeleteMapping("/cancella")
    public String delete(@PathVariable Long id){
        if(docenteService.deleteDocente(id).isPresent()){
            return "Eliminato!";
        }
        return "Id non trovato!";
    }

    @GetMapping("/convert")
    public List<DocenteDTO> getListaDocenteDto(){
        return docenteService.getListaDocenteDto();
    }

    @GetMapping("/findDocenteByNome/{nome}")
    public List<Docente> findByNome(@PathVariable String nome){
        return docenteService.findByNome(nome);
    }

    //REST TEMPLATE CONNECTION
    @GetMapping(value = "/getDocente/{id}", produces = "application/json")
    public Docente getDocenteById(@PathVariable Long id){
        return docenteService.findDocenteById(id);
    }

    //REST TEMPLATE CONNECTION
    @GetMapping(value = "/findByNomeAndCognome/{nome}/{cognome}")
    public Docente findByNomeAndCognome(@PathVariable String nome, @PathVariable String cognome){
        return docenteService.findByNomeAndCognome(nome,cognome);
    }



}
