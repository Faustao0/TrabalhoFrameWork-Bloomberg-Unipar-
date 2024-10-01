package com.example.MaterializeCSS.Repository;

import com.example.MaterializeCSS.Domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}
