package com.example.agencyadmin.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.Service.FairService;
import com.example.agencyadmin.DTO.FairDTO;
import com.example.agencyadmin.Models.Fair;


@RestController
@RequestMapping("/api/fairs")
public class FairController {
    private final FairService fairService;

    public FairController(FairService fairService){
        this.fairService = fairService;
    }

    @GetMapping
    public ResponseEntity<List<FairDTO>> getAllFairs() {
        List<FairDTO> fairs = fairService.getAllFairs();
        return ResponseEntity.ok(fairs);
    }
    @GetMapping("/{agencyId}")
    public ResponseEntity<List<FairDTO>> getFairsByAgencyId(@PathVariable String agencyId) {
        List<FairDTO> fairs = fairService.getFairByAgencyId(agencyId);

        return ResponseEntity.ok(fairs);
    }
    @PostMapping
    public ResponseEntity<Fair> createFair(@RequestBody FairDTO fairDto) {
        Fair createdFair = fairService.createFair(fairDto);
        return ResponseEntity.ok(createdFair);
    }
    @PutMapping("/{fairId}")
    public ResponseEntity<Fair> updateFair( @PathVariable UUID fairId, @RequestBody FairDTO fairDto) {
        Fair updatedFair = fairService.updateFair(fairId, fairDto);
        return ResponseEntity.ok(updatedFair);
    }   
    @DeleteMapping("/{fairId}")
    public ResponseEntity<Void> deleteFair(@PathVariable UUID fairId) {
        fairService.deleteFair(fairId);
        return ResponseEntity.noContent().build();
    }


    
}
