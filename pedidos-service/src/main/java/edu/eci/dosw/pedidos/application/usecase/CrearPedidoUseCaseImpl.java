package edu.eci.dosw.pedidos.application.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.eci.dosw.pedidos.domain.model.CrearPedidoRequest;
import edu.eci.dosw.pedidos.domain.model.PedidoCreadoEvent;
import edu.eci.dosw.pedidos.domain.port.in.CrearPedidoUseCase;
import edu.eci.dosw.pedidos.domain.port.out.PedidoEventPublisher;

@Service
public class CrearPedidoUseCaseImpl implements CrearPedidoUseCase {

    private final PedidoEventPublisher publisher;

    public CrearPedidoUseCaseImpl(PedidoEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String crear(CrearPedidoRequest pedido) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        publisher.publicarPedidoCreado(new PedidoCreadoEvent(
                id, pedido.cliente(), pedido.arepas(), LocalDateTime.now().toString()));
        return id;
    }
}
