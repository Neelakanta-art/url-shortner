package com.projectSam.projects.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "urls")
public class UrlEntity {
    @Id
    private String id; // The short URL key
    private String originalUrl;
    private long expirationDate; // Store expiration in milliseconds since epoch


}
