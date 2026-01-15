package com.example.agencyadmin.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseEntity<BusReviewDTO> createReview(@RequestBody BusReviewDTO dto) {
        return ResponseEntity.ok(service.createReview(dto));
    }
}
