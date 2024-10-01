package com.example.MaterializeCSS.Service;

import com.example.MaterializeCSS.Domain.Habito;
import com.example.MaterializeCSS.Repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitoService {
    @Autowired
    private HabitoRepository habitoRepository;

    public Habito salvar(Habito habito) {
        return habitoRepository.save(habito);
    }

    // Outros métodos de negócio
}