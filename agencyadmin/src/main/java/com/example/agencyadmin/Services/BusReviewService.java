package com.example.agencyadmin.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTOs.BusReviewDTO;
import com.example.agencyadmin.Mappers.BusReviewMapper;
import com.example.agencyadmin.Models.BusReview;
import com.example.agencyadmin.Repositories.BusReviewRepository;

@Service
public class BusReviewService {

    private final BusReviewRepository repository;
    private final BusReviewMapper mapper;

    public BusReviewService(BusReviewRepository repository, BusReviewMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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

    public BusReviewDTO createReview(BusReviewDTO dto) {
        BusReview entity = mapper.toEntity(dto);
        // Ensure bus relationship is valid if necessary calling BusRepository,
        // but here we trust the ID or let DB constraint fail if invalid.
        return mapper.toDTO(repository.save(entity));
    }

    public Optional<BusReviewDTO> updateReview(UUID id, BusReviewDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setCustomerName(dto.getCustomerName());
            existing.setRating(dto.getRating());
            existing.setComment(dto.getComment());
            // Bus relationship and createdAt are usually not updated in reviews
            return mapper.toDTO(repository.save(existing));
        });
    }

    public boolean deleteReview(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
