package com.example.agencyadmin.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

import com.example.agencyadmin.DTOs.BusReviewDTO;
import com.example.agencyadmin.Services.BusReviewService;

@RestController
@RequestMapping("/api/bus-reviews")
public class BusReviewController {

    private final BusReviewService service;

    public BusReviewController(BusReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BusReviewDTO>> getAllReviews() {
        return ResponseEntity.ok(service.getAllReviews());
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<BusReviewDTO> getReviewById(@PathVariable UUID reviewId) {
        return service.getReviewById(reviewId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BusReviewDTO> createReview(@RequestBody BusReviewDTO dto) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(service.createReview(dto));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<BusReviewDTO> updateReview(@PathVariable UUID reviewId, @RequestBody BusReviewDTO dto) {
        return service.updateReview(reviewId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId) {
        if (service.deleteReview(reviewId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
