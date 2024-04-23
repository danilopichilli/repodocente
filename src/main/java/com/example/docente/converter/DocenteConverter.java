package com.example.docente.converter;

import com.example.docente.dto.DocenteDTO;
import com.example.docente.entity.Docente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocenteConverter {

    public DocenteDTO convertToDto(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setNome(docente.getNome());
        return docenteDTO;
    }

    public Docente convertToEntity(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        docente.setNome(docenteDTO.getNome());
        return docente;
    }

    public List<DocenteDTO> convertEntityToDto(List<Docente> docenteList) {
        List<DocenteDTO> docenteDTOList = new ArrayList<>();
        for (Docente docente : docenteList) {
            docenteDTOList.add(convertToDto(docente));
        }
        return docenteDTOList;
    }
}
