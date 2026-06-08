package org.example.taskservice.controller;

import jakarta.validation.Valid;
import org.example.taskservice.model.Tarefa;
import org.example.taskservice.service.TarefaService;
import org.springframework.web.bind.annotation.*;
import org.example.taskservice.dto.TarefaDTO;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public List<TarefaDTO> listarTodas() {
        return service.listarTodasComUsuario();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Tarefa não encontrada"));
    }

    @PostMapping
    public Tarefa criarTarefa(
            @Valid @RequestBody Tarefa tarefa) {

        return service.salvar(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Tarefa tarefa) {

        return service.atualizar(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}
