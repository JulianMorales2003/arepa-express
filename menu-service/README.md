# menu-service

Servicio de Arepa Express que expone el menú (`GET /menu`). Es el primer servicio publicado detrás del API Gateway (Azure API Management) bajo el prefijo `menu-api`.

## Ejecutar en local

```bash
mvn spring-boot:run
curl http://localhost:8080/menu
```

## Desplegar en Azure Container Apps

```bash
mvn -q package -DskipTests
az containerapp up --name menu-service --resource-group $RG --location $LOC \
  --environment arepas-env --source . --ingress external --target-port 8080
```

Java 21 · Spring Boot 3.5 · Maven
