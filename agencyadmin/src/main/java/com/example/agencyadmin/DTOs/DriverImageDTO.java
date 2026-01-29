package com.example.agencyadmin.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for DriverImage entity.
 * This DTO is used to transfer driver image information between layers of the
 * application.
 * It encapsulates image metadata stored in S3 buckets.
 */
public class DriverImageDTO {

    /** The unique identifier for the image */
    private UUID imageId;

    /** Reference to the driver that this image belongs to */
    private UUID driverId;

    /** The Cloudinary public ID for the image */
    private String publicId;

    /** The full URL to access the image */
    private String imageUrl;

    /** The original file name */
    private String fileName;

    /** The MIME content type (e.g., image/jpeg, image/png) */
    private String contentType;

    /** The file size in bytes */
    private Long fileSize;

    /** Flag indicating if this is the primary/main image */
    private Boolean isPrimary;

    /** Timestamp when the image was uploaded */
    private LocalDateTime uploadedAt;

    /** Optional description of the image */
    private String description;

    // ===== Constructors =====

    /**
     * Default constructor for DriverImageDTO
     */
    public DriverImageDTO() {
    }

    /**
     * Constructor with all fields
     */
    public DriverImageDTO(UUID imageId, UUID driverId, String publicId,
            String imageUrl, String fileName, String contentType, Long fileSize,
            Boolean isPrimary, LocalDateTime uploadedAt, String description) {
        this.imageId = imageId;
        this.driverId = driverId;
        this.publicId = publicId;
        this.imageUrl = imageUrl;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.isPrimary = isPrimary;
        this.uploadedAt = uploadedAt;
        this.description = description;
    }

    // ===== Getters and Setters =====

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
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
