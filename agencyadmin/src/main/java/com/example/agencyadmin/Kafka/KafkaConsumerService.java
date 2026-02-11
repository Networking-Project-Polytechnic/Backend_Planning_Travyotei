package com.example.agencyadmin.Kafka;

import com.example.agencyadmin.DTOs.ScheduleDTO;
import com.example.agencyadmin.DTOs.ScheduleDetailsDTO;
import com.example.agencyadmin.Elasticsearch.ScheduleIndex;
import com.example.agencyadmin.Elasticsearch.ScheduleElasticsearchRepository;
import com.example.agencyadmin.Services.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class KafkaConsumerService {

    @Lazy
    @Autowired(required = false)
    private ScheduleElasticsearchRepository scheduleElasticsearchRepository;

    @Autowired
    private ScheduleService scheduleService;

    @KafkaListener(topics = KafkaTopicConfig.SCHEDULE_TOPIC, groupId = "agencyadmin-group")
    public void consumeScheduleUpdate(ScheduleDTO scheduleDTO) {
        log.info("Consumed schedule update for indexing: {}", scheduleDTO.getScheduleid());

        // Fetch full details to populate the index correctly
        Optional<ScheduleDetailsDTO> detailsOpt = scheduleService.getScheduleDetails(scheduleDTO.getScheduleid());

        if (detailsOpt.isPresent()) {
            ScheduleDetailsDTO details = detailsOpt.get();

            ScheduleIndex indexEntry = ScheduleIndex.builder()
                    .scheduleid(details.getScheduleid().toString())
                    .date(details.getDate())
                    .departuretime(details.getDeparturetime())
                    .arrivaltime(details.getArrivaltime())
                    .agencyid(details.getAgencyid())
                    .startlocationname(details.getStartLocation() != null ? details.getStartLocation().getLocationname()
                            : "Unknown")
                    .endlocationname(
                            details.getEndLocation() != null ? details.getEndLocation().getLocationname() : "Unknown")
                    .price(details.getPrice() != null ? details.getPrice().getPriceAmount().doubleValue() : 0.0)
                    .busid(details.getBus() != null ? details.getBus().getBusId().toString() : null)
                    .busmake(details.getBusMakeName())
                    .busmodel(details.getBusModelName())
                    .build();

            if (scheduleElasticsearchRepository != null) {
                try {
                    scheduleElasticsearchRepository.save(indexEntry);
                    log.info("Successfully indexed schedule {} into Elasticsearch", details.getScheduleid());
                } catch (Exception e) {
                    log.error("Failed to index schedule {}: {}", details.getScheduleid(), e.getMessage());
                }
            } else {
                log.warn("Elasticsearch not available, skipping indexing for schedule {}", details.getScheduleid());
            }
        } else {
            log.warn("Could not fetch details for schedule {} to index", scheduleDTO.getScheduleid());
        }
    }
}
