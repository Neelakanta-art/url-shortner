package com.projectSam.projects.controller;

import com.projectSam.projects.DTO.UrlDTO;
import com.projectSam.projects.DTO.UrlResponseDTO;
import com.projectSam.projects.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    // Endpoint to create a short URL
    @PostMapping("/create")
    public ResponseEntity<UrlResponseDTO> createShortUrl(@RequestBody UrlDTO urlDTO) {
        UrlResponseDTO response = urlService.createShortUrl(urlDTO);
        return ResponseEntity.ok(response);
    }

    // Endpoint to redirect to the original URL
    @GetMapping("/{shortKey}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortKey) {
        return urlService.redirectToOriginalUrl(shortKey);
    }
}
