package com.example.tasktrackerbootstrap.repository;

import com.example.tasktrackerbootstrap.domain.HabitoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HabitoHistoricoRepository extends JpaRepository<HabitoHistorico, Long> {

    List<HabitoHistorico> findByHabito_Id(Long habitoId);
}
