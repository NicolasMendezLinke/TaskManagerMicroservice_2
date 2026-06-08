package org.example.userservice.controller;

import org.example.userservice.model.Usuario;
import org.example.userservice.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));
    }

    @PostMapping
    public Usuario criarUsuario(
            @Valid @RequestBody Usuario usuario) {

        return service.salvar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody Usuario usuario) {

        return service.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(
            @PathVariable Long id) {

        service.deletar(id);
    }
}
