package com.moon.gigeco.api.profile.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ProfileResponse {

    private Long id;
    private String name;
    private int views;
    private LocalDateTime createdAt;
}
