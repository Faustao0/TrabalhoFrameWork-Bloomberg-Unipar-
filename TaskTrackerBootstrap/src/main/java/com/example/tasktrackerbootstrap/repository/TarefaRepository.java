package com.example.tasktrackerbootstrap.repository;

import com.example.tasktrackerbootstrap.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuario_Id(Long Id);
}