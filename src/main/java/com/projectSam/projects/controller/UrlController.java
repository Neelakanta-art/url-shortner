package com.projectSam.projects.controller;

import com.projectSam.projects.DTO.UrlDTO;
import com.projectSam.projects.DTO.UrlResponseDTO;
import com.projectSam.projects.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponseDTO> createShortUrl(@RequestBody UrlDTO urlDTO) {
        UrlResponseDTO response = urlService.createShortUrl(urlDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortKey) {
        String originalUrl = urlService.getOriginalUrl(shortKey);
        return ResponseEntity.ok(originalUrl);
    }
}
