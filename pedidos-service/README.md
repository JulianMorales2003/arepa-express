# pedidos-service

Recibe pedidos (`POST /pedidos`) y publica el evento `pedido.creado` en el exchange `arepas.eventos` de RabbitMQ. Arquitectura hexagonal: el caso de uso (`CrearPedidoUseCase` + `Impl`) depende del puerto de salida `PedidoEventPublisher`; RabbitMQ es solo el adaptador.

```
domain/model, domain/port/in, domain/port/out
application/usecase
infrastructure/adapter/in/rest, infrastructure/adapter/out/messaging, infrastructure/config
```

## Ejecutar en local

```bash
docker run -d -p 5672:5672 -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=arepas -e RABBITMQ_DEFAULT_PASS=arepas123 rabbitmq:3-management
mvn spring-boot:run
curl -X POST http://localhost:8080/pedidos -H "Content-Type: application/json" \
  -d '{"cliente":"Laura","arepas":["choclo","huevo"]}'
```

## Variables de entorno

`RABBIT_HOST`, `RABBIT_USER`, `RABBIT_PASS` (por defecto `localhost`, `arepas`, `arepas123`).

## Desplegar en Azure Container Apps

```bash
mvn -q package -DskipTests
az containerapp up --name pedidos-service --resource-group $RG --environment arepas-env \
  --source . --ingress external --target-port 8080 --env-vars $RABBIT_ENV
```

Java 21 · Spring Boot 3.5 · Maven
