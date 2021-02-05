package br.com.caelum.eats.pagamento;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpPagamentoConfig {

    static final String topicName = "eats-pagamento";
    static final String queueName = "pagamento-confirmado";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("pagamentos");
    }

}
