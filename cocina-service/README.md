# cocina-service

Consumidor de eventos. Su cola durable `cocina.pedidos` está unida al exchange `arepas.eventos` con la routing key `pedido.*`; por cada pedido registra en el log que lo prepara (3 s simulados) y que quedó listo.

## Variables de entorno

`RABBIT_HOST`, `RABBIT_USER`, `RABBIT_PASS`.

## Desplegar en Azure Container Apps

No recibe tráfico HTTP, así que se fija una réplica siempre encendida:

```bash
mvn -q package -DskipTests
az containerapp up --name cocina-service --resource-group $RG --environment arepas-env \
  --source . --ingress internal --target-port 8080 --env-vars $RABBIT_ENV
az containerapp update --name cocina-service --resource-group $RG --min-replicas 1 --max-replicas 1
```

Java 21 · Spring Boot 3.5 · Maven
