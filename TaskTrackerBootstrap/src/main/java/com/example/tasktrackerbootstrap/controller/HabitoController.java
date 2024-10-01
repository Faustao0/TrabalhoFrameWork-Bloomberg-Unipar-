package com.example.tasktrackerbootstrap.controller;

import com.example.tasktrackerbootstrap.domain.Habito;
import com.example.tasktrackerbootstrap.domain.HabitoHistorico;
import com.example.tasktrackerbootstrap.repository.HabitoRepository;
import com.example.tasktrackerbootstrap.service.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/habitos")
public class HabitoController {

    @Autowired
    private HabitoService habitoService;
    @Autowired
    private HabitoRepository habitoRepository;

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("")
    public ResponseEntity<List<Habito>> listarHabitos(Principal principal) {
        String username = principal.getName();
        List<Habito> habitos = habitoService.listarTodosHabitos();

        if (habitos == null || habitos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(habitos);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/salvar/{usuarioId}")
    public ResponseEntity<String> salvarHabito(@RequestBody Habito habito, @PathVariable Long usuarioId) {
        try {
            habitoService.salvarHabito(habito, usuarioId);
            return ResponseEntity.ok("Hábito salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao salvar o hábito: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Habito>> listarHabitosPorUsuario(@PathVariable Long id) {
        List<Habito> habitos = habitoService.listarHabitosPorUsuario(id);
        return ResponseEntity.ok(habitos);
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<List<HabitoHistorico>> listarHistoricoPorHabito(@PathVariable Long id) {
        List<HabitoHistorico> historicos = habitoService.listarHistoricoPorHabito(id);
        return ResponseEntity.ok(historicos);
    }
}