package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.ScheduleDTO;
import com.example.agencyadmin.DTOs.ScheduleDetailsDTO;
import com.example.agencyadmin.Models.Schedule;
import com.example.agencyadmin.Repositories.ScheduleRepository;
import com.example.agencyadmin.Mappers.ScheduleMapper;

/**
 * Service class for Schedule entity.
 * This service encapsulates business logic for Schedule operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to schedules should be implemented in this
 * service.
 */
@Service
public class ScheduleService {

    /** The Schedule repository for database operations */
    @Autowired
    private ScheduleRepository scheduleRepository;

    /** The Schedule mapper for converting between entities and DTOs */
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private BusService busService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RoutePriceService routePriceService;
    @Autowired
    private BusImageService busImageService;
    @Autowired
    private BusReviewService busReviewService;
    @Autowired
    private DriverImageService driverImageService;
    @Autowired
    private BusTypeService busTypeService;
    @Autowired
    private BusMakeService busMakeService;
    @Autowired
    private BusModelService busModelService;

    /**
     * Get comprehensive details for a specific schedule
     * 
     * @param scheduleId the ID of the schedule
     * @return detailed schedule DTO if found
     */
    public Optional<ScheduleDetailsDTO> getScheduleDetails(UUID scheduleId) {
        return scheduleRepository.findById(scheduleId).map(schedule -> {
            ScheduleDetailsDTO details = new ScheduleDetailsDTO();
            details.setScheduleid(schedule.getScheduleid());
            details.setDate(schedule.getDate());
            details.setArrivaltime(schedule.getArrivaltime());
            details.setDeparturetime(schedule.getDeparturetime());
            details.setAgencyid(schedule.getAgencyid());

            // 1. Fetch Bus Details
            busService.getBusById(schedule.getBusid()).ifPresent(bus -> {
                details.setBus(bus);
                details.setBusImages(busImageService.getBusImagesByBusIds(List.of(bus.getBusId())));
                details.setBusReviews(busReviewService.getBusReviewsByBusIds(List.of(bus.getBusId())));

                // Reference data names
                busTypeService.getBusTypeById(bus.getBusTypeId())
                        .ifPresent(t -> details.setBusTypeName(t.getBusTypeName()));
                busMakeService.getBusMakeById(bus.getBusMakeId())
                        .ifPresent(m -> details.setBusMakeName(m.getMakeName()));
                busModelService.getBusModelById(bus.getBusModelId())
                        .ifPresent(m -> details.setBusModelName(m.getModelName()));
            });

            // 2. Fetch Route Details
            routeService.getRouteById(schedule.getRouteid()).ifPresent(route -> {
                details.setRoute(route);
                details.setStopPoints(route.getStopPoints());
                locationService.getLocationById(route.getStartlocationid()).ifPresent(details::setStartLocation);
                locationService.getLocationById(route.getEndlocationid()).ifPresent(details::setEndLocation);
            });

            // 3. Fetch Price Details
            routePriceService.getRoutePriceById(schedule.getPriceid()).ifPresent(details::setPrice);

            // 4. Fetch Driver Details
            if (schedule.getDriverid() != null) {
                driverService.getDriverById(schedule.getDriverid()).ifPresent(driver -> {
                    details.setDriver(driver);
                    details.setDriverImages(
                            driverImageService.getDriverImagesByDriverIds(List.of(driver.getDriverId())));
                });
            }

            return details;
        });
    }

    /**
     * Create a new schedule
     * 
     * @param scheduleDTO the schedule data transfer object
     * @return the created schedule DTO
     */
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toDTO(savedSchedule);
    }

    /**
     * Create a new schedule for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param scheduleDTO the schedule data
     * @return the created schedule DTO
     */
    public ScheduleDTO createScheduleScoped(String agencyId, ScheduleDTO scheduleDTO) {
        scheduleDTO.setAgencyid(agencyId);
        return createSchedule(scheduleDTO);
    }

    /**
     * Get a schedule by its ID
     * 
     * @param scheduleId the ID of the schedule
     * @return the schedule DTO if found
     */
    public Optional<ScheduleDTO> getScheduleById(UUID scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        return schedule.map(scheduleMapper::toDTO);
    }

    /**
     * Get all schedules
     * 
     * @return list of all schedule DTOs
     */
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return list of schedule DTOs for the agency
     */
    public List<ScheduleDTO> getSchedulesByAgency(String agencyId) {
        List<Schedule> schedules = scheduleRepository.findByAgencyid(agencyId);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific route
     * 
     * @param routeId the ID of the route
     * @return list of schedule DTOs for the route
     */
    public List<ScheduleDTO> getSchedulesByRoute(UUID routeId) {
        List<Schedule> schedules = scheduleRepository.findByRouteid(routeId);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific bus
     * 
     * @param busId the ID of the bus
     * @return list of schedule DTOs for the bus
     */
    public List<ScheduleDTO> getSchedulesByBus(UUID busId) {
        List<Schedule> schedules = scheduleRepository.findByBusid(busId);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific date
     * 
     * @param date the date of the schedules
     * @return list of schedule DTOs for the date
     */
    public List<ScheduleDTO> getSchedulesByDate(String date) {
        List<Schedule> schedules = scheduleRepository.findByDate(date);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get schedules for an agency with optional date and departure time filters
     * 
     * @param agencyId      the ID of the agency
     * @param date          optional date filter
     * @param departureTime optional departure time filter
     * @return list of matching schedule DTOs
     */
    public List<ScheduleDTO> getSchedulesByFilters(String agencyId, String date, String departureTime) {
        List<Schedule> schedules;
        if (date != null && departureTime != null) {
            schedules = scheduleRepository.findByAgencyidAndDateAndDeparturetime(agencyId, date, departureTime);
        } else if (date != null) {
            schedules = scheduleRepository.findByAgencyidAndDate(agencyId, date);
        } else {
            schedules = scheduleRepository.findByAgencyid(agencyId);
        }
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Update an existing schedule
     * 
     * @param scheduleId  the ID of the schedule to update
     * @param scheduleDTO the updated schedule data
     * @return the updated schedule DTO
     */
    public Optional<ScheduleDTO> updateSchedule(UUID scheduleId, ScheduleDTO scheduleDTO) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(scheduleId);
        if (existingSchedule.isPresent()) {
            Schedule schedule = existingSchedule.get();
            schedule.setDate(scheduleDTO.getDate());
            schedule.setArrivaltime(scheduleDTO.getArrivaltime());
            schedule.setDeparturetime(scheduleDTO.getDeparturetime());
            schedule.setRouteid(scheduleDTO.getRouteid());
            schedule.setBusid(scheduleDTO.getBusid());
            schedule.setAgencyid(scheduleDTO.getAgencyid());
            schedule.setPriceid(scheduleDTO.getPriceid());
            schedule.setDriverid(scheduleDTO.getDriverid());
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            return Optional.of(scheduleMapper.toDTO(updatedSchedule));
        }

        return Optional.empty();
    }

    /**
     * Update an existing schedule for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param scheduleId  the ID of the schedule to update
     * @param scheduleDTO the updated schedule data
     * @return the updated schedule DTO if successful
     */
    public Optional<ScheduleDTO> updateScheduleScoped(String agencyId, UUID scheduleId, ScheduleDTO scheduleDTO) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(scheduleId);
        if (existingSchedule.isPresent() && existingSchedule.get().getAgencyid().equals(agencyId)) {
            scheduleDTO.setAgencyid(agencyId); // Ensure agencyid remains correct
            return updateSchedule(scheduleId, scheduleDTO);
        }
        return Optional.empty();
    }

    /**
     * Delete a schedule by its ID
     * 
     * @param scheduleId the ID of the schedule to delete
     * @return true if deletion was successful
     */
    public boolean deleteSchedule(UUID scheduleId) {
        if (scheduleRepository.existsById(scheduleId)) {
            scheduleRepository.deleteById(scheduleId);
            return true;
        }
        return false;
    }

    /**
     * Delete a schedule for a specific agency
     * 
     * @param agencyId   the ID of the agency
     * @param scheduleId the ID of the schedule to delete
     * @return true if deletion was successful
     */
    public boolean deleteScheduleScoped(String agencyId, UUID scheduleId) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(scheduleId);
        if (existingSchedule.isPresent() && existingSchedule.get().getAgencyid().equals(agencyId)) {
            return deleteSchedule(scheduleId);
        }
        return false;
    }

    /**
     * Search for schedules across all agencies filtering by route locations and
     * date
     * 
     * @param startLocationId the start location ID
     * @param stopLocationId  the end location ID
     * @param date            the date of travel
     * @return list of detailed schedules matching the criteria
     */
    public List<ScheduleDetailsDTO> searchGlobalSchedules(UUID startLocationId, UUID stopLocationId, String date) {
        List<Schedule> schedules = scheduleRepository.findGlobalSchedules(startLocationId, stopLocationId, date);
        return schedules.stream()
                .map(s -> getScheduleDetails(s.getScheduleid()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    /**
     * Search for schedules across all agencies filtering by route location names
     * and date
     * 
     * @param start the start location name
     * @param stop  the end location name
     * @param date  the date of travel
     * @return list of detailed schedules matching the criteria
     */
    public List<ScheduleDetailsDTO> searchGlobalSchedulesByName(String start, String stop, String date) {
        List<Schedule> schedules = scheduleRepository.findGlobalSchedulesByName(start, stop, date);
        return schedules.stream()
                .map(s -> getScheduleDetails(s.getScheduleid()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
