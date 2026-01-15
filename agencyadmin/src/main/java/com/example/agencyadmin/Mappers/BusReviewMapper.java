package com.example.agencyadmin.Mappers;

import org.springframework.stereotype.Component;

import com.example.agencyadmin.DTOs.BusReviewDTO;
import com.example.agencyadmin.Models.Bus;
import com.example.agencyadmin.Models.BusReview;

@Component
public class BusReviewMapper {

    public BusReviewDTO toDTO(BusReview review) {
        if (review == null) {
            return null;
        }

        BusReviewDTO dto = new BusReviewDTO();
        dto.setReviewId(review.getReviewId());
        // Handling lazy loading or null bus safely
        dto.setBusId(review.getBus() != null ? review.getBus().getBusId() : null);
        dto.setCustomerName(review.getCustomerName());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }

    public BusReview toEntity(BusReviewDTO dto) {
        if (dto == null) {
            return null;
        }

        BusReview review = new BusReview();
        review.setReviewId(dto.getReviewId());

        if (dto.getBusId() != null) {
            Bus bus = new Bus();
            bus.setBusId(dto.getBusId());
            review.setBus(bus);
        }

        review.setCustomerName(dto.getCustomerName());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        // CreatedAt is usually managed by Database or set explicitly if needed
        return review;
    }
}
