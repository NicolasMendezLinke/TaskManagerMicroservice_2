package org.example.userservice.producer;

import org.example.userservice.config.RabbitMQConfig;
import org.example.userservice.event.UsuarioCriadoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProducer {

    private final RabbitTemplate rabbitTemplate;

    public UsuarioProducer(
            RabbitTemplate rabbitTemplate
    ) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarUsuarioCriado(
            UsuarioCriadoEvent event
    ) {

        String mensagem =
                event.getUsuarioId()
                        + "|"
                        + event.getNome();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.FILA_USUARIO_CRIADO,
                mensagem
        );

        System.out.println(
                "Evento enviado: "
                        + mensagem
        );
    }
}
