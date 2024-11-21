package com.ssafy.trip.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private List<String> errors;
}