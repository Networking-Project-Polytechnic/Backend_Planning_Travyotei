package com.example.agencyadmin.Kafka;

import com.example.agencyadmin.DTOs.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendScheduleUpdate(ScheduleDTO scheduleDTO) {
        log.info("Sending schedule update to Kafka: {}", scheduleDTO.getScheduleid());
        kafkaTemplate.send(KafkaTopicConfig.SCHEDULE_TOPIC, scheduleDTO.getScheduleid().toString(), scheduleDTO);
    }
}
