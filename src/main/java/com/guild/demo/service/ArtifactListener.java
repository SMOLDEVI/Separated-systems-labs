package com.guild.demo.service;

import com.guild.demo.config.RabbitMQConfig;
import com.guild.demo.entity.Artifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ArtifactListener {
    private static final Logger log = LoggerFactory.getLogger(ArtifactListener.class);

    @RabbitListener(queues = RabbitMQConfig.MAIN_QUEUE)
    public void receiveMessage(Artifact artifact) {
        log.info("<<<< [ПОЛУЧЕНО СООБЩЕНИЕ] Артефакт: {}", artifact.getName());

        if ("Тьма".equalsIgnoreCase(artifact.getElement())) {
            log.error("!!!! ОШИБКА: Артефакты Тьмы запрещены! Отправка в DLQ...");
            throw new AmqpRejectAndDontRequeueException("Запрещенная стихия");
        }

        log.info("<<<< Сообщение успешно обработано.");
    }
}