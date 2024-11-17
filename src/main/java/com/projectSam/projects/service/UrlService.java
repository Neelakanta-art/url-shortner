package com.projectSam.projects.service;

import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpHeaders;

public interface UrlService {

    public interface UrlService {

        UrlResponseDTO shortenUrl(UrlRequestDTO data, HttpServletRequest request);

        HttpHeaders redirect(String id);
    }
}
