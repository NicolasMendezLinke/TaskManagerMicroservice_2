package org.example.userservice.controller;

import org.example.userservice.model.HistoricoUsuario;
import org.example.userservice.repository.HistoricoUsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
@CrossOrigin(origins = "http://localhost:3000")
public class HistoricoUsuarioController {

    private final HistoricoUsuarioRepository repository;

    public HistoricoUsuarioController(
            HistoricoUsuarioRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public List<HistoricoUsuario> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{usuarioId}")
    public List<HistoricoUsuario> listarHistorico(
            @PathVariable Long usuarioId) {

        return repository.findByUsuarioId(usuarioId);
    }
}
