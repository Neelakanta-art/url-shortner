package com.projectSam.projects.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UrlServiceImpl {




        private final UrlRepository urlRepository;

        @Override
        public UrlResponseDTO shortenUrl(UrlRequestDTO data, HttpServletRequest request) {
            String id;

            do {
                id = RandomStringUtils.randomAlphanumeric(5, 10);
            } while (urlRepository.existsById(id));

            urlRepository.save(new UrlEntity(id, data.url(), LocalDateTime.now().plusMinutes(1)));

            String redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);

            return new UrlResponseDTO(data.url(), redirectUrl);
        }

        @Override
        public HttpHeaders redirect(String id) {
            UrlEntity url = urlRepository.findById(id)
                    .orElseThrow(() -> new UrlNotFoundException(id));
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(url.getUrl()));
            return headers;
        }

}
