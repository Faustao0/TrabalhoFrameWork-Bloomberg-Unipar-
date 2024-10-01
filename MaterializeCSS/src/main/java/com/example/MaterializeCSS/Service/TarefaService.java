package com.example.MaterializeCSS.Service;

import com.example.MaterializeCSS.Domain.Tarefa;
import com.example.MaterializeCSS.Repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Outros métodos de negócio
}