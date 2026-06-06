package org.example.userservice.service;

import org.example.userservice.model.HistoricoUsuario;
import org.example.userservice.model.Usuario;
import org.example.userservice.repository.HistoricoUsuarioRepository;
import org.example.userservice.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final HistoricoUsuarioRepository historicoRepository;

    public UsuarioService(
            UsuarioRepository repository,
            HistoricoUsuarioRepository historicoRepository) {

        this.repository = repository;
        this.historicoRepository = historicoRepository;
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {

        Usuario salvo = repository.save(usuario);

        HistoricoUsuario historico =
                new HistoricoUsuario(
                        salvo.getId(),
                        salvo.getNome(),
                        "CRIADO",
                        LocalDateTime.now()
                );

        historicoRepository.save(historico);

        return salvo;
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        String nomeAntigo = usuario.getNome();

        usuario.setNome(usuarioAtualizado.getNome());

        Usuario atualizado = repository.save(usuario);

        HistoricoUsuario historico =
                new HistoricoUsuario(
                        atualizado.getId(),
                        atualizado.getNome(),
                        "EDITADO de " +
                                nomeAntigo +
                                " para " +
                                atualizado.getNome(),
                        LocalDateTime.now()
                );

        historicoRepository.save(historico);

        return atualizado;
    }

    public void deletar(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        HistoricoUsuario historico =
                new HistoricoUsuario(
                        usuario.getId(),
                        usuario.getNome(),
                        "EXCLUIDO",
                        LocalDateTime.now()
                );

        historicoRepository.save(historico);

        repository.delete(usuario);
    }
}
