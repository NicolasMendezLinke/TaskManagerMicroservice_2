package org.example.taskservice.service;

import org.example.taskservice.model.Tarefa;
import org.example.taskservice.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository repository;
    private final RestTemplate restTemplate;

    public TarefaService(TarefaRepository repository,
                         RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Tarefa salvar(Tarefa tarefa) {

        validarUsuario(tarefa.getUsuarioId());

        if (tarefa.getStatus() == null || tarefa.getStatus().isBlank()) {
            tarefa.setStatus("PENDENTE");
        }

        return repository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa tarefaAtualizada) {

        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tarefa não encontrada"));

        validarUsuario(tarefaAtualizada.getUsuarioId());

        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setStatus(tarefaAtualizada.getStatus());
        tarefa.setUsuarioId(tarefaAtualizada.getUsuarioId());

        return repository.save(tarefa);
    }

    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }

        repository.deleteById(id);
    }

    // Validação entre serviços
    private void validarUsuario(Long usuarioId) {

        if (usuarioId == null) {
            throw new RuntimeException("usuarioId não pode ser nulo");
        }

        String url = "http://localhost:8080/usuarios/" + usuarioId;

        try {
            restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
