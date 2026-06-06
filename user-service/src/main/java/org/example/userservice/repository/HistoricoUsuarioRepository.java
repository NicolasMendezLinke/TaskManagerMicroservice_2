package org.example.userservice.repository;

import org.example.userservice.model.HistoricoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoUsuarioRepository
        extends JpaRepository<HistoricoUsuario, Long> {

    List<HistoricoUsuario> findByUsuarioId(Long usuarioId);
}
