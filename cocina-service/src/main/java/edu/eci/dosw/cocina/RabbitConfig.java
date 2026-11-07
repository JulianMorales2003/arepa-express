package edu.eci.dosw.cocina;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange arepasExchange() {
        return new TopicExchange("arepas.eventos");
    }

    @Bean
    public Queue cola() {
        return new Queue("cocina.pedidos", true);   // true = durable
    }

    @Bean
    public Binding binding(Queue cola, TopicExchange arepasExchange) {
        return BindingBuilder.bind(cola).to(arepasExchange).with("pedido.*");
    }

    @Bean
    public MessageConverter jsonConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setAlwaysConvertToInferredType(true);   // usa el tipo del parametro del listener
        return converter;
    }
}
