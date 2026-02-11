package com.example.agencyadmin.Elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;
import java.util.UUID;

public interface ScheduleElasticsearchRepository extends ElasticsearchRepository<ScheduleIndex, String> {

        List<ScheduleIndex> findByStartlocationnameAndEndlocationnameAndDate(
                        String startlocationname,
                        String endlocationname,
                        String date);

        List<ScheduleIndex> findByStartlocationnameContainingIgnoreCaseOrEndlocationnameContainingIgnoreCase(
                        String start,
                        String stop);
}
