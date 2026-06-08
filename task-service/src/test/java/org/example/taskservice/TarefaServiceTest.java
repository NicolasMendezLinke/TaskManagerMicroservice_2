package org.example.taskservice;

import org.example.taskservice.model.Tarefa;
import org.example.taskservice.repository.TarefaRepository;
import org.example.taskservice.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void deveSalvarTarefaComStatusPadrao() {

        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo("Estudar");
        tarefa.setDescricao("Spring Boot");
        tarefa.setUsuarioId(1L);

        when(restTemplate.getForObject(
                anyString(),
                eq(Object.class)))
                .thenReturn(new Object());

        when(repository.save(any(Tarefa.class)))
                .thenAnswer(i -> i.getArgument(0));

        Tarefa resultado =
                tarefaService.salvar(tarefa);

        assertEquals(
                "PENDENTE",
                resultado.getStatus()
        );

        verify(repository, times(1))
                .save(any(Tarefa.class));
    }
}
