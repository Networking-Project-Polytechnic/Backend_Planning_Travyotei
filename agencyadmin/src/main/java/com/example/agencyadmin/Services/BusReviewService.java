package com.example.agencyadmin.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.agencyadmin.DTOs.BusReviewDTO;
import com.example.agencyadmin.Mappers.BusReviewMapper;
import com.example.agencyadmin.Models.BusReview;
import com.example.agencyadmin.Repositories.BusReviewRepository;

@Service
@Transactional(readOnly = true)
public class BusReviewService {

    private final BusReviewRepository repository;
    private final BusReviewMapper mapper;
    private final com.example.agencyadmin.Repositories.BusRepository busRepository;

    public BusReviewService(BusReviewRepository repository, BusReviewMapper mapper,
            com.example.agencyadmin.Repositories.BusRepository busRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.busRepository = busRepository;
    }

    public List<BusReviewDTO> getAllReviews() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<BusReviewDTO> getReviewById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public List<BusReviewDTO> getBusReviewsByBusIds(List<UUID> busIds) {
        return repository.findByBusBusIdIn(busIds).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BusReviewDTO createReview(BusReviewDTO dto) {
        BusReview entity = mapper.toEntity(dto);
        // Ensure bus relationship is valid if necessary calling BusRepository,
        // but here we trust the ID or let DB constraint fail if invalid.
        return mapper.toDTO(repository.save(entity));
    }

    /**
     * Create a new review for a bus belonging to a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param dto      the review data
     * @return the created review DTO
     */
    public Optional<BusReviewDTO> createReviewScoped(String agencyId, BusReviewDTO dto) {
        Optional<com.example.agencyadmin.Models.Bus> bus = busRepository.findById(dto.getBusId());
        if (bus.isPresent() && bus.get().getAgencyId().toString().equals(agencyId)) {
            return Optional.of(createReview(dto));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<BusReviewDTO> updateReview(UUID id, BusReviewDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setCustomerName(dto.getCustomerName());
            existing.setRating(dto.getRating());
            existing.setComment(dto.getComment());
            // Bus relationship and createdAt are usually not updated in reviews
            return mapper.toDTO(repository.save(existing));
        });
    }

    @Transactional
    public boolean deleteReview(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Delete a review if the associated bus belongs to the specific agency
     * 
     * @param agencyId the ID of the agency
     * @param id       the ID of the review to delete
     * @return true if deletion was successful
     */
    public boolean deleteReviewScoped(String agencyId, UUID id) {
        Optional<BusReview> review = repository.findById(id);
        if (review.isPresent() && review.get().getBus().getAgencyId().toString().equals(agencyId)) {
            return deleteReview(id);
        }
        return false;
    }
}
