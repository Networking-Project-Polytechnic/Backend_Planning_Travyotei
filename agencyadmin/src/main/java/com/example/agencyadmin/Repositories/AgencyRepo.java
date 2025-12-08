package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.agencyadmin.Models.Schedule;
@Repository
public interface AgencyRepo extends  JpaRepository<Schedule, UUID> {
 // Filter schedules by date, route, and agency
  @Query(value = """
      SELECT 
          s.scheduleid,
          s.departuretime,
          s.arrivaltime,
          b.buscapacity,
          f.fairamount
      FROM schedule s
      JOIN buses b ON s.busid = b.busid
      JOIN fair f ON s.fairid = f.fairid
      JOIN route r ON s.routeid = r.routeid
      WHERE (:routeId IS NULL OR s.routeid = :routeId)
        AND (:agencyId IS NULL OR s.agencyid = :agencyId)
        AND (:date IS NULL OR s.date = :date)
      ORDER BY s.date, s.departuretime
  """, nativeQuery = true)
  List<Object[]> filterSchedule(
     UUID routeId,
     String agencyId,
     String date
  );

        //
}
