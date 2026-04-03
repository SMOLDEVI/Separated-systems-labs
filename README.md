# Проект Гильдии Магов

## Быстрый запуск (Локальная сборка)

Для экономии сетевого трафика и ускорения развертывания используется локальная сборка артефакта.

1. **Сборка проекта (Maven):**

   ```bash
   ./mvnw clean package -DskipTests
   ```

2. **Запуск всей инфраструктуры (Docker Compose):**
   ```bash
   docker compose up -d --build
   ```

## Ссылки

- Swagger: http://localhost:8080/swagger-ui/index.html
- RabbitMQ: http://localhost:15672 (guest/guest)

```

```
