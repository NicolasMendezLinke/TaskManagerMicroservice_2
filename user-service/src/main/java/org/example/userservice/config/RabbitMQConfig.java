package org.example.userservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_USUARIO_CRIADO =
            "usuario.criado";

    @Bean
    public Queue filaUsuarioCriado() {
        return new Queue(
                FILA_USUARIO_CRIADO,
                true
        );
    }
}
