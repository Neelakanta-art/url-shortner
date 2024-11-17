package com.projectSam.projects.entity;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrlEntity {

    @Id
    private String id;

    private String url;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiresAt;
}
