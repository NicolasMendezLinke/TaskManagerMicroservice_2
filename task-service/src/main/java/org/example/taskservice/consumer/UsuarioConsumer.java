package org.example.taskservice.consumer;

import org.example.taskservice.event.UsuarioCriadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConsumer {

    @RabbitListener(queues = "usuario.criado")
    public void receberUsuarioCriado(
            String mensagem
    ) {

        System.out.println(
                "=================================="
        );

        System.out.println(
                "EVENTO RECEBIDO NO TASK-SERVICE"
        );

        System.out.println(
                mensagem
        );

        System.out.println(
                "=================================="
        );
    }
}
