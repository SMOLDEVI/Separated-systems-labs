package com.guild.demo.service;

import com.guild.demo.config.RabbitMQConfig;
import com.guild.demo.model.Artifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ArtifactListener {

    private static final Logger log = LoggerFactory.getLogger(ArtifactListener.class);

    // ЛАБА 3: Метод постоянно слушает очередь в RabbitMQ
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(Artifact artifact) {
        // Логирование входящего сообщения
        log.info("<<<< [ВХОДЯЩЕЕ СООБЩЕНИЕ] Система Уведомлений получила сообщение из RabbitMQ!");
        log.info("<<<< Детали артефакта: ID = {}, Название = '{}', Стихия = '{}', Сила = {}", 
                artifact.getId(), artifact.getName(), artifact.getElement(), artifact.getPowerLevel());
    }
}
