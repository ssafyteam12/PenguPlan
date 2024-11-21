package com.ssafy.trip.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionSearchResponse {
    private List<AttractionDTO> attractions;
    private int totalCount;
    private int currentPage;
    private int totalPages;
}
