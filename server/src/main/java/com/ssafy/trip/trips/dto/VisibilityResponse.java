package com.ssafy.trip.trips.dto;

import groovy.lang.DelegatesTo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisibilityResponse {
    private Boolean visibility;
}
