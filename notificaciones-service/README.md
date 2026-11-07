# notificaciones-service

Consumidor de eventos. Su cola durable `notificaciones.pedidos` está unida al exchange `arepas.eventos` con la routing key `pedido.creado`; por cada pedido registra en el log el aviso al cliente.

## Variables de entorno

`RABBIT_HOST`, `RABBIT_USER`, `RABBIT_PASS`.

## Desplegar en Azure Container Apps

```bash
mvn -q package -DskipTests
az containerapp up --name notificaciones-service --resource-group $RG --environment arepas-env \
  --source . --ingress internal --target-port 8080 --env-vars $RABBIT_ENV
az containerapp update --name notificaciones-service --resource-group $RG --min-replicas 1 --max-replicas 1
```

Java 21 · Spring Boot 3.5 · Maven
