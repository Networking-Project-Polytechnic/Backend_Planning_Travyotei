package com.example.agencyadmin.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * Entity representing an image associated with a Bus.
 * Images are stored in S3 buckets, and this entity stores the metadata.
 */
@Entity(name = "bus_image")
public class BusImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID imageId;

    @NotNull
    @Column(name = "bus_id", nullable = false)
    private UUID busId;

    @NotNull
    @Column(name = "public_id", nullable = false, unique = true)
    private String publicId;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "description")
    private String description;

    // ===== Constructors =====

    public BusImage() {
        this.uploadedAt = LocalDateTime.now();
    }

    public BusImage(UUID busId, String publicId, String fileName) {
        this.busId = busId;
        this.publicId = publicId;
        this.fileName = fileName;
        this.uploadedAt = LocalDateTime.now();
    }

    // ===== Getters and Setters =====

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public UUID getBusId() {
        return busId;
    }

    public void setBusId(UUID busId) {
        this.busId = busId;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
