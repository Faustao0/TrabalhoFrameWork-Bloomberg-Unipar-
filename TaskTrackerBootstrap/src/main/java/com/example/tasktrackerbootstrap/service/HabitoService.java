package com.example.tasktrackerbootstrap.service;

import com.example.tasktrackerbootstrap.domain.Habito;
import com.example.tasktrackerbootstrap.domain.HabitoHistorico;
import com.example.tasktrackerbootstrap.domain.Tarefa;
import com.example.tasktrackerbootstrap.domain.Usuario;
import com.example.tasktrackerbootstrap.repository.HabitoHistoricoRepository;
import com.example.tasktrackerbootstrap.repository.HabitoRepository;
import com.example.tasktrackerbootstrap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitoService {

    @Autowired
    private HabitoHistoricoRepository habitoHistoricoRepository;

    @Autowired
    private HabitoRepository habitoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Habito> listarHabitosPorUsuario(Long usuarioId) {
        return habitoRepository.findByUsuario_Id(usuarioId);
    }

    public List<Habito> listarTodosHabitos() {
        return habitoRepository.findAll();
    }

    public void salvarHabito(Habito habito, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        habito.setUsuario(usuario);

        if (habito.getHistoricos().stream().noneMatch(h -> h.getData().equals(LocalDate.now()))) {
            HabitoHistorico historicoInicial = new HabitoHistorico();
            historicoInicial.setData(LocalDate.now());
            historicoInicial.setHabito(habito);
            habito.getHistoricos().add(historicoInicial);
        }

        habitoRepository.save(habito);
    }

    public List<HabitoHistorico> listarHistoricoPorHabito(Long habitoId) {
        return habitoHistoricoRepository.findByHabito_Id(habitoId);
    }
}