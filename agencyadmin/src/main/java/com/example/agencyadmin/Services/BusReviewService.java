package com.example.agencyadmin.Services;

import java.util.List;
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

    public BusReviewDTO createReview(BusReviewDTO dto) {
        BusReview entity = mapper.toEntity(dto);
        // Ensure bus relationship is valid if necessary calling BusRepository,
        // but here we trust the ID or let DB constraint fail if invalid.
        return mapper.toDTO(repository.save(entity));
    }
}
