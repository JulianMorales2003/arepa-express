package edu.eci.dosw.pedidos.infrastructure.adapter.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import edu.eci.dosw.pedidos.domain.model.PedidoCreadoEvent;
import edu.eci.dosw.pedidos.domain.port.out.PedidoEventPublisher;
import edu.eci.dosw.pedidos.infrastructure.config.RabbitConfig;

@Component
public class RabbitPedidoEventPublisherImpl implements PedidoEventPublisher {

    private final RabbitTemplate rabbit;

    public RabbitPedidoEventPublisherImpl(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void publicarPedidoCreado(PedidoCreadoEvent evento) {
        rabbit.convertAndSend(RabbitConfig.EXCHANGE, "pedido.creado", evento);
    }
}
