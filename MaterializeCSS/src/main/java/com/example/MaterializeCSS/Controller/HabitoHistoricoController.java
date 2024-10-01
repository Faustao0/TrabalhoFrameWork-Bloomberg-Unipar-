package com.example.MaterializeCSS.Controller;

import com.example.MaterializeCSS.Domain.HabitoHistorico;
import com.example.MaterializeCSS.Service.HabitoHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habitoHistorico")
public class HabitoHistoricoController {
    @Autowired
    private HabitoHistoricoService habitoHistoricoService;

    @PostMapping("/salvar")
    public HabitoHistorico salvar(@RequestBody HabitoHistorico habitoHistorico) {
        return habitoHistoricoService.salvar(habitoHistorico);
    }

    // Outros endpoints
}
