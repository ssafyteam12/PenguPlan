package com.ssafy.trip.trips.service;

import com.ssafy.trip.trips.dto.TripsDto;

import java.util.List;

public interface TripsService {
    public void savePlan(TripsDto tripsDto);
    public TripsDto getPlan(int tripId);
    public void modifyPlan(int tripId, TripsDto tripsDto);
    public void deletePlan(int tripId);
    public List<TripsDto> getAllPlans(int offset, int size);
    public Integer copyPlan(int tripId);
    public Boolean togglePublic(int tripId);
}
