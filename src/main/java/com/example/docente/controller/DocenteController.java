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
        }else{
            docenteService.createDocente(docente);
            return "Salvato!";
        }
    }

    @GetMapping("/lista")
    public List<Docente> getAllDocenti(){
        return docenteService.getAllDocentes();
    }

    @GetMapping("/cerca/{id}")
    public Docente findDocenteById(@PathVariable long id){
        return docenteService.getDocenteById(id);
    }

    @PutMapping("/modifica/{id}") //modifica/1
    public void update(@RequestBody Docente docente, @PathVariable long id){
        docenteService.updateDocente(docente,id);
    }

    @DeleteMapping("/cancella{id}") //cancella?id=1
    public String delete(@RequestParam long id){
        if(id != docenteService.getDocenteById(id).getId()){
            return "Il Docente non esiste! Cambiare id!";
        }else{
            docenteService.deleteDocente(id);
            return "Eliminato!";
        }
    }

    @GetMapping("/convert")
    public List<DocenteDTO> convert(){
        return docenteService.convert();
    }

    @GetMapping("/findByNome{nome}")
    public List<Docente> findByNome(@RequestParam String nome){
        return docenteService.findByNome(nome);
    }


}
