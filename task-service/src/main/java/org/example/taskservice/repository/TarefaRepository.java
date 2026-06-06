package org.example.taskservice.repository;

import org.example.taskservice.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository
        extends JpaRepository<Tarefa, Long> {
}
