package com.projectSam.projects.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
public class UrlEntity {
    @Id
    private String id; // The short URL key
    private String originalUrl;
    private long expirationDate; // Store expiration in milliseconds since epoch

    // Constructors, Getters, and Setters
    public UrlEntity() {}

    public UrlEntity(String id, String originalUrl, long expirationDate) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }
}

