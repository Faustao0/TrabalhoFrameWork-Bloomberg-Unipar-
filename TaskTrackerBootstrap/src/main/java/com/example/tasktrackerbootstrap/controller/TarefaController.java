package com.example.tasktrackerbootstrap.controller;

import com.example.tasktrackerbootstrap.domain.Tarefa;
import com.example.tasktrackerbootstrap.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/todas")
    public ResponseEntity<List<Tarefa>> listarTodasTarefas() {
        List<Tarefa> todasTarefas = tarefaService.listarTodasTarefas();
        return ResponseEntity.ok(todasTarefas);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarTarefa(@RequestBody Tarefa tarefa, @RequestParam Long usuarioId) {
        try {
            tarefaService.salvarTarefa(tarefa, usuarioId);
            return ResponseEntity.status(201).body("Tarefa salva com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno ao salvar a tarefa.");
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Tarefa>> listarTarefasPorUsuario(@PathVariable Long id) {
        List<Tarefa> tarefas = tarefaService.listarTarefasPorUsuario(id);
        return ResponseEntity.ok(tarefas);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarStatusTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        try {
            tarefaService.atualizarStatus(id, tarefaAtualizada.isConcluida());
            return ResponseEntity.ok("Status da tarefa atualizado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao atualizar o status da tarefa.");
        }
    }
}