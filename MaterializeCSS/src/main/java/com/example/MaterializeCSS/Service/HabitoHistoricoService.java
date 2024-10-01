package com.example.MaterializeCSS.Service;

import com.example.MaterializeCSS.Domain.HabitoHistorico;
import com.example.MaterializeCSS.Repository.HabitoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitoHistoricoService {
    @Autowired
    private HabitoHistoricoRepository habitoHistoricoRepository;

    public HabitoHistorico salvar(HabitoHistorico habitoHistorico) {
        return habitoHistoricoRepository.save(habitoHistorico);
    }

    // Outros métodos de negócio
}
