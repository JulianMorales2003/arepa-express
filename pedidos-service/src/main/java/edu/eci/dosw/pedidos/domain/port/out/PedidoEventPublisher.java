package edu.eci.dosw.pedidos.domain.port.out;

import edu.eci.dosw.pedidos.domain.model.PedidoCreadoEvent;

public interface PedidoEventPublisher {
    void publicarPedidoCreado(PedidoCreadoEvent evento);
}
