package edu.eci.dosw.pedidos.domain.port.in;

import edu.eci.dosw.pedidos.domain.model.CrearPedidoRequest;

public interface CrearPedidoUseCase {
    String crear(CrearPedidoRequest pedido);
}
