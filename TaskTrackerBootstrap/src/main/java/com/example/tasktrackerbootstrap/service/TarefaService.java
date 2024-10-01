package com.example.tasktrackerbootstrap.service;

import com.example.tasktrackerbootstrap.domain.Tarefa;
import com.example.tasktrackerbootstrap.domain.Usuario;
import com.example.tasktrackerbootstrap.repository.TarefaRepository;
import com.example.tasktrackerbootstrap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Tarefa> listarTarefasPorUsuario(Long Id) {
        return tarefaRepository.findByUsuario_Id(Id);
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public void salvarTarefa(Tarefa tarefa, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        tarefa.setUsuario(usuario);

        tarefaRepository.save(tarefa);
    }

    public void atualizarStatus(Long tarefaId, boolean concluida) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefa.setConcluida(concluida);
        tarefaRepository.save(tarefa);
    }
}