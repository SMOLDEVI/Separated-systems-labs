# Микросервис Гильдии Магов (Spring Boot + PostgreSQL + RabbitMQ)

## 1. Запуск инфраструктуры (Docker)

Запустите базу данных PostgreSQL:
```bash
docker run -d --name postgres-db -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=guild_db postgres:15
```

Запустите брокер сообщений RabbitMQ:
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

## 2. Запуск приложения

Сборка проекта (в корне папки с проектом):
```bash
./mvnw clean install
```

Запуск Spring Boot сервера:
```bash
./mvnw spring-boot:run
```
*(При первом запуске Flyway автоматически создаст таблицы в базе данных).*

## 3. Ссылки для работы

После запуска приложения, все интерфейсы доступны в браузере:

* 📜 **Swagger UI (REST API):** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* 🐇 **RabbitMQ UI (Брокер сообщений):** [http://localhost:15672](http://localhost:15672) *(Логин: `guest`, Пароль: `guest`)*
