package com.guild.demo.controller;

import com.guild.demo.config.RabbitMQConfig;
import com.guild.demo.dto.ArtifactDto;
import com.guild.demo.entity.Artifact;
import com.guild.demo.repository.ArtifactRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artifacts")
public class ArtifactController {

    private static final Logger log = LoggerFactory.getLogger(ArtifactController.class);

    private final ArtifactRepository repository; 
    private final RabbitTemplate rabbitTemplate;

    public ArtifactController(ArtifactRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public List<Artifact> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Artifact create(@Valid @RequestBody ArtifactDto dto) {
        Artifact artifact = new Artifact();
        artifact.setName(dto.getName());
        artifact.setElement(dto.getElement());
        artifact.setPowerLevel(dto.getPowerLevel());

        Artifact savedArtifact = repository.save(artifact);

        log.info(">>>> Отправка в брокер: {}", savedArtifact.getName());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, savedArtifact);

        return savedArtifact;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}