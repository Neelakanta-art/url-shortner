package com.projectSam.projects.service;

import com.projectSam.projects.DTO.UrlDTO;
import com.projectSam.projects.DTO.UrlResponseDTO;
import com.projectSam.projects.entity.UrlEntity;
import com.projectSam.projects.exceptions.UrlNotFoundException;
import com.projectSam.projects.repo.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Generate short URL and store in the database
    public UrlResponseDTO createShortUrl(UrlDTO urlDTO) {
        String originalUrl = urlDTO.getOriginalUrl();
        int expirationDays = urlDTO.getExpirationDays();

        // Validate expirationDays
        if (expirationDays <= 0) {
            throw new IllegalArgumentException("Expiration days must be greater than zero");
        }

        // Generate a short key
        String shortKey = generateShortKey(originalUrl);

        // Calculate expiration time
        long expirationTime = Instant.now().toEpochMilli() + expirationDays * 86400000L;

        // Save to database
        UrlEntity urlEntity = new UrlEntity(shortKey, originalUrl, expirationTime);
        urlRepository.save(urlEntity);

        // Return the short URL
        return new UrlResponseDTO("http://localhost:8080/api/" + shortKey);
    }

    // Redirect to the original URL
    public RedirectView redirectToOriginalUrl(String shortKey) {
        UrlEntity urlEntity = urlRepository.findById(shortKey)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found"));

        // Check if the short URL has expired
        if (Instant.now().toEpochMilli() > urlEntity.getExpirationDate()) {
            urlRepository.delete(urlEntity); // Remove expired entry
            throw new UrlNotFoundException("Short URL has expired");
        }

        // Redirect to the original URL
        return new RedirectView(urlEntity.getOriginalUrl());
    }

    // Generate a unique short key using Base64 encoding
    private String generateShortKey(String originalUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(originalUrl.getBytes());
            return Base64.getUrlEncoder().encodeToString(hash).substring(0, 6); // First 6 characters
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short key", e);
        }
    }


    public String getOriginalUrl(String shortKey) {
        // Retrieve the URL entity from the repository
        UrlEntity urlEntity = urlRepository.findById(shortKey)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found"));

        // Check if the short URL has expired
        if (Instant.now().toEpochMilli() > urlEntity.getExpirationDate()) {
            // Remove expired entry from the database
            urlRepository.delete(urlEntity);
            throw new UrlNotFoundException("Short URL has expired");
        }

        // Return the original URL
        return urlEntity.getOriginalUrl();
    }
}
