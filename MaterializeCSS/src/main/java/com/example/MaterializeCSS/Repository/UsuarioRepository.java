package com.example.MaterializeCSS.Repository;

import com.example.MaterializeCSS.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
