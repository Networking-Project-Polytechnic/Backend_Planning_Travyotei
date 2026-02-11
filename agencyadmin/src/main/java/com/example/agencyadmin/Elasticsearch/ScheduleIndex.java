package com.example.agencyadmin.Elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "schedules", createIndex = true)
public class ScheduleIndex {

    @Id
    private String scheduleid;

    @Field(type = FieldType.Text, name = "date")
    private String date;

    @Field(type = FieldType.Text, name = "departuretime")
    private String departuretime;

    @Field(type = FieldType.Text, name = "arrivaltime")
    private String arrivaltime;

    @Field(type = FieldType.Keyword, name = "agencyid")
    private String agencyid;

    @Field(type = FieldType.Text, name = "startlocationname")
    private String startlocationname;

    @Field(type = FieldType.Text, name = "endlocationname")
    private String endlocationname;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

    @Field(type = FieldType.Keyword, name = "busid")
    private String busid;

    @Field(type = FieldType.Text, name = "busmake")
    private String busmake;

    @Field(type = FieldType.Text, name = "busmodel")
    private String busmodel;
}
