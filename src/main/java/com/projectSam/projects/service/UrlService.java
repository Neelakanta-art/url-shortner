package com.projectSam.projects.service;

import com.projectSam.projects.DTO.UrlDTO;
import com.projectSam.projects.DTO.UrlResponseDTO;
import com.projectSam.projects.entity.UrlEntity;
import com.projectSam.projects.exceptions.UrlNotFoundException;
import com.projectSam.projects.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponseDTO createShortUrl(UrlDTO urlDTO) {
        String originalUrl = urlDTO.getOriginalUrl();
        int expirationDays = urlDTO.getExpirationDays();

        // Generate a unique short key
        String shortKey = Base64.getUrlEncoder()
                .encodeToString(originalUrl.getBytes())
                .substring(0, 6); // First 6 characters

        long expirationTime = Instant.now().toEpochMilli() + (expirationDays * 86400000L);

        UrlEntity urlEntity = new UrlEntity(shortKey, originalUrl, expirationTime);
        urlRepository.save(urlEntity);

        return new UrlResponseDTO("http://short.ly/" + shortKey);
    }

    public String getOriginalUrl(String shortKey) {
        UrlEntity urlEntity = urlRepository.findById(shortKey)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found"));

        // Check expiration
        if (Instant.now().toEpochMilli() > urlEntity.getExpirationDate()) {
            urlRepository.delete(urlEntity);
            throw new UrlNotFoundException("Short URL has expired");
        }

        return urlEntity.getOriginalUrl();
    }
}
