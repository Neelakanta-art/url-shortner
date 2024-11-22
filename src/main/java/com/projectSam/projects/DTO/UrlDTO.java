package com.projectSam.projects.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlDTO {
    private String originalUrl;
    private int expirationDays; // Optional: Expiration duration in days
}
