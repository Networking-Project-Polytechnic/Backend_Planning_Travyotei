package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTOs.*;

@Service
public class AgencyService {

    @Autowired
    private BusService busService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private BusImageService busImageService;
    @Autowired
    private BusReviewService busReviewService;
    @Autowired
    private DriverImageService driverImageService;
    @Autowired
    private RoutePriceService routePriceService;
    @Autowired
    private BusMakeService busMakeService;
    @Autowired
    private BusManufacturerService busManufacturerService;
    @Autowired
    private BusModelService busModelService;
    @Autowired
    private BusTypeService busTypeService;
    @Autowired
    private FuelTypeService fuelTypeService;
    @Autowired
    private TransmissionTypeService transmissionTypeService;
    @Autowired
    private VehicleAmenityService vehicleAmenityService;
    @Autowired
    private BusCanTransportService busCanTransportService;

    public AgencyOverviewDTO getAgencyOverview(String agencyId, String date, String departureTime) {
        AgencyOverviewDTO overview = new AgencyOverviewDTO();
        overview.setAgencyId(agencyId);

        // 1. Fetch filtered schedules first
        List<ScheduleDTO> schedules = scheduleService.getSchedulesByFilters(agencyId, date, departureTime);
        overview.setSchedules(schedules);

        // 2. Fetch Direct Agency Entities (Filtered if date/time provided)
        List<BusDTO> allBuses = busService.getBusesByAgency(agencyId);
        List<RouteDTO> allRoutes = routeService.getRoutesByAgency(agencyId);
        List<LocationDTO> allLocations = locationService.getLocationsByAgency(agencyId);

        if (date != null || departureTime != null) {
            // Snapshot mode: only show assets linked to filtered schedules
            java.util.Set<UUID> activeBusIds = schedules.stream().map(ScheduleDTO::getBusid)
                    .collect(java.util.stream.Collectors.toSet());
            java.util.Set<UUID> activeRouteIds = schedules.stream().map(ScheduleDTO::getRouteid)
                    .collect(java.util.stream.Collectors.toSet());

            List<BusDTO> filteredBuses = allBuses.stream().filter(b -> activeBusIds.contains(b.getBusId())).toList();
            List<RouteDTO> filteredRoutes = allRoutes.stream().filter(r -> activeRouteIds.contains(r.getRouteid()))
                    .toList();

            java.util.Set<UUID> activeLocationIds = new java.util.HashSet<>();
            filteredRoutes.forEach(r -> {
                activeLocationIds.add(r.getStartlocationid());
                activeLocationIds.add(r.getEndlocationid());
            });
            List<LocationDTO> filteredLocations = allLocations.stream()
                    .filter(l -> activeLocationIds.contains(l.getLocationid())).toList();

            overview.setBuses(filteredBuses);
            overview.setRoutes(filteredRoutes);
            overview.setLocations(filteredLocations);
        } else {
            // Full Overview mode
            overview.setBuses(allBuses);
            overview.setRoutes(allRoutes);
            overview.setLocations(allLocations);
        }

        List<DriverDTO> drivers = driverService.getDriversByAgency(agencyId);
        overview.setDrivers(drivers);

        // 2. Child Entities (Batch Fetched based on what is in overview)
        List<UUID> busIds = overview.getBuses().stream().map(BusDTO::getBusId).toList();
        if (!busIds.isEmpty()) {
            overview.setBusImages(busImageService.getBusImagesByBusIds(busIds));
            overview.setBusReviews(busReviewService.getBusReviewsByBusIds(busIds));
        } else {
            overview.setBusImages(List.of());
            overview.setBusReviews(List.of());
        }

        List<UUID> driverIds = drivers.stream().map(DriverDTO::getDriverId).toList();
        if (!driverIds.isEmpty()) {
            overview.setDriverImages(driverImageService.getDriverImagesByDriverIds(driverIds));
        } else {
            overview.setDriverImages(List.of());
        }

        List<UUID> routeIds = overview.getRoutes().stream().map(RouteDTO::getRouteid).toList();
        if (!routeIds.isEmpty()) {
            overview.setRoutePrices(routePriceService.getPricesByRouteIds(routeIds));
        } else {
            overview.setRoutePrices(List.of());
        }

        // 3. Global Reference Data (Now filtered by Agency)
        overview.setBusMakes(busMakeService.getBusMakesByAgency(agencyId));
        overview.setBusManufacturers(busManufacturerService.getBusManufacturersByAgency(agencyId));
        overview.setBusModels(busModelService.getBusModelsByAgency(agencyId));
        overview.setBusTypes(busTypeService.getBusTypesByAgency(agencyId));
        overview.setFuelTypes(fuelTypeService.getFuelTypesByAgency(agencyId));
        overview.setTransmissionTypes(transmissionTypeService.getTransmissionTypesByAgency(agencyId));
        overview.setVehicleAmenities(vehicleAmenityService.getAmenitiesByAgency(agencyId));
        overview.setBusCanTransport(busCanTransportService.getTransportablesByAgency(agencyId));

        return overview;
    }
}
