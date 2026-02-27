package com.guild.demo.controller;

import com.guild.demo.config.RabbitMQConfig;
import com.guild.demo.model.Artifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/artifacts")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    private final Map<Integer, Artifact> vault = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);
    
    // Инструмент для работы с RabbitMQ
    private final RabbitTemplate rabbitTemplate;

    // Конструктор: инициализируем RabbitTemplate и добавляем стартовые данные
    public Controller(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        vault.put(idGenerator.get(), new Artifact(idGenerator.getAndIncrement(), "Меч Огня", "Огонь", 9000));
        vault.put(idGenerator.get(), new Artifact(idGenerator.getAndIncrement(), "Кольцо Невидимости", "Воздух", 500));
    }

    // --- GET Методы (Лаба 2) ---

    @GetMapping("/status/string")
    public String getStatus() { return "Хранилище гильдии работает."; }

    @GetMapping("/status/int")
    public int getArtifactCount() { return vault.size(); }

    @GetMapping("/status/char")
    public char getVaultRank() { return 'S'; }

    @GetMapping
    public Collection<Artifact> getAllArtifacts() { return vault.values(); }

    @GetMapping("/{id}")
    public Artifact getArtifactById(@PathVariable int id) { return vault.get(id); }

    @GetMapping("/search")
    public Artifact getArtifactByElement(@RequestParam String element) {
        return vault.values().stream()
                .filter(a -> a.getElement().equalsIgnoreCase(element))
                .findFirst()
                .orElse(null);
    }

    // --- POST Метод (Лаба 2 + Лаба 3) ---
    @PostMapping
    public Artifact addArtifact(@RequestBody Artifact newArtifact) {
        int newId = idGenerator.getAndIncrement();
        newArtifact.setId(newId);
        vault.put(newId, newArtifact);

        // ЛАБА 3: Логирование исходящего сообщения и отправка в RabbitMQ
        log.info(">>>> [ИСХОДЯЩЕЕ СООБЩЕНИЕ] Создан новый артефакт. Отправка в RabbitMQ: {}", newArtifact.getName());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, newArtifact);
        log.info(">>>> [ИСХОДЯЩЕЕ СООБЩЕНИЕ] Сообщение успешно отправлено в брокер!");

        return newArtifact; 
    }

    // --- PUT и DELETE Методы (Лаба 2) ---
    @PutMapping("/{id}")
    public Artifact upgradeArtifact(@PathVariable int id, @RequestBody Artifact updatedData) {
        if (vault.containsKey(id)) {
            updatedData.setId(id);
            vault.put(id, updatedData);
            return updatedData;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String destroyArtifact(@PathVariable int id) {
        if (vault.remove(id) != null) {
            return "Артефакт с ID " + id + " был успешно уничтожен.";
        }
        return "Артефакт не найден.";
    }
}
