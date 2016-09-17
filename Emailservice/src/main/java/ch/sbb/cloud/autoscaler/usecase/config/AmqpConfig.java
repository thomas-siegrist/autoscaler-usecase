package ch.sbb.cloud.autoscaler.usecase.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by thomas on 04.09.16.
 */
@Configuration
public class AmqpConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public Queue createEmailQueue() {
        Queue queue = new Queue("email-queue", true, false, false);
        queue.setAdminsThatShouldDeclare(amqpAdmin);
        queue.setShouldDeclare(true);
        return queue;
    }

}
