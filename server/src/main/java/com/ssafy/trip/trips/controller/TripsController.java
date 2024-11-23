package com.ssafy.trip.trips.controller;

import com.ssafy.trip.trips.dto.*;
import com.ssafy.trip.trips.service.TripsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trips")
@Log4j2
public class TripsController {

    private final TripsService tripsService;

    @PostMapping
    public ResponseEntity<?> createPlans(@RequestBody TripsRequest tripsRequest){
        Long userId = getCurrentUserId();
        System.out.println(tripsRequest.getContent() +" is content.");
        TripsDto tripsDto = TripsDto.of(tripsRequest, userId.intValue());
        tripsService.savePlan(tripsDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<?> getPlans(@PathVariable int tripId){
        log.info("get trips by id: {}", tripId);
        TripsDto tripsDto = tripsService.getPlan(tripId);

        if(tripsDto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디가 "+tripId +"인 여행 정보는 존재하지 않습니다.");
        }
        return ResponseEntity.ok(tripsDto);
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<?> modifyPlan(@PathVariable int tripId, @RequestBody TripsDto tripsDto){
        tripsService.modifyPlan(tripId, tripsDto);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deletePlan(@PathVariable int tripId){
        tripsService.deletePlan(tripId);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/public")
    public ResponseEntity<AllTripsResponse> getPublicTrips(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<TripsDto> trips = tripsService.getAllPlans(page, size);
        AllTripsResponse allTripsResponse = new AllTripsResponse(page, size, trips);
        return ResponseEntity.ok(allTripsResponse);
    }

    @PostMapping("/{tripId}/copy")
    public ResponseEntity<CopyTripResponse> copyTrip(@PathVariable int tripId) {
        Integer newTripId = tripsService.copyPlan(tripId);
        return ResponseEntity.ok(new CopyTripResponse(newTripId));
    }

    @PutMapping("/{tripId}/visibility")
    public ResponseEntity<?> updateVisibility(
            @PathVariable int tripId
    ) {
        Boolean isPublic = tripsService.togglePublic(tripId);
        VisibilityResponse visibilityResponse = new VisibilityResponse(isPublic);
        return ResponseEntity.ok(visibilityResponse);
    }

    private Long getCurrentUserId() {

        String temp = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = 1L;
        log.info("userId = {}", temp);
        if (!temp.equals("anonymousUser")) userId = Long.parseLong(temp);
        return userId;
    }
}
