package com.example.docente.repository;

import com.example.docente.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    List<Docente> findByNome(String nome);
}
