package com.example.tasktrackerbootstrap.repository;

import com.example.tasktrackerbootstrap.domain.Habito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitoRepository extends JpaRepository<Habito, Long> {

    List<Habito> findByUsuario_Id(Long Id);
}
