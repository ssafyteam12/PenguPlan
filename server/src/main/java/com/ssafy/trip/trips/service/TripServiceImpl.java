package com.ssafy.trip.trips.service;

import com.ssafy.trip.trips.dto.TripsDto;
import com.ssafy.trip.trips.repository.TripsMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripsService {
    private final TripsMapper tripsMapper;

    @Override
    @Transactional
    public void savePlan(TripsDto tripsDto){
        tripsMapper.insertTrip(tripsDto);
        if (tripsDto.getAttractions().isEmpty()) return;
        tripsMapper.insertTripAttractions(tripsDto.getTripId(), tripsDto.getAttractions());
    }

    @Override
    public TripsDto getPlan(int tripId){
        return tripsMapper.getTripById(tripId);
    }

    @Override
    @Transactional
    public void modifyPlan(int tripId, TripsDto tripsDto){
        tripsMapper.updateTrip(tripId,tripsDto);
        tripsMapper.deleteTripAttraction(tripId);
        tripsMapper.insertTripAttractions(tripId,tripsDto.getAttractions());
    }

    @Override
    @Transactional
    public void deletePlan(int tripId){
        tripsMapper.deleteTripAttraction(tripId);
        tripsMapper.deleteTrip(tripId);
    }

    @Override
    public List<TripsDto> getAllPlans(int page, int size) {
        return tripsMapper.getAllTrips((page - 1) * size , size);
    }

    @Override
    @Transactional
    public Integer copyPlan(int tripId) {
        // 1. 기존 여행 계획 조회
        TripsDto originalTrip = tripsMapper.getTripById(tripId);
        tripsMapper.updateCopied(tripId);
        // 2. 새로운 여행 계획 객체 생성 (기존 데이터 복사)
        TripsDto newTrip = TripsDto.builder()
                .userId(getCurrentUserId().intValue()) // 현재 로그인한 사용자 ID
                .content(originalTrip.getContent())
                .sidoCode(originalTrip.getSidoCode())
                .startDate(originalTrip.getStartDate())
                .endDate(originalTrip.getEndDate())
                .attractions(originalTrip.getAttractions())
                .build();

        // 3. 새 여행 계획 저장 (이 시점에서 newTrip.getTripId()로 새 ID를 얻을 수 있음)
        tripsMapper.insertTrip(newTrip);

        // 4. 여행지 정보도 복사
        if (newTrip.getAttractions() != null && !newTrip.getAttractions().isEmpty()) {
            tripsMapper.insertTripAttractions(newTrip.getTripId(), newTrip.getAttractions());
        }

        // 5. 새로 생성된 여행 계획의 ID 반환
        return newTrip.getTripId();
    }

    @Override
    public Boolean togglePublic(int tripId) {
        tripsMapper.togglePublic(tripId);
        return tripsMapper.getPublic(tripId);
    }

    private Long getCurrentUserId() {

        String temp = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = 1L;
        log.info("userId = {}", temp);
        if (!temp.equals("anonymousUser")) userId = Long.parseLong(temp);
        return userId;
    }
}
