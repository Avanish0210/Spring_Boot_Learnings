package com.example.SecurityApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String userId;
    @Column(nullable = false)
    private String token;
    @Column(unique = true , nullable = false )
    private LocalDateTime createdAt = LocalDateTime.now();

}
