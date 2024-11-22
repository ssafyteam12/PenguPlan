import axios from "axios";
import apiClient from "../ApiClient";
import { Attraction, SendTrip, UserTrips } from "@/type/type";

// 완료
export const postUserTrip = async (sendData: Object) => {
  try {
    const response = await apiClient.post<SendTrip>("/api/v1/trips", sendData);
  } catch (error) {
    console.error(error);
  }
};

export const putUserTrip = async (tripId: number, sendData: Object) => {
  try {
    const response = await apiClient.put(`/api/v1/trips/${tripId}`, sendData);
  } catch (error) {
    console.error(error);
  }
};
// 완료
export const getUserTripAll = async () => {
  try {
    const response = await apiClient.get<UserTrips>("/api/v1/users/me/trips");
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 완료
export const getUserTripById = async (tripId: number) => {
  try {
    const response = await apiClient.get(`/api/v1/trips/${tripId}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 완료
export const deleteUserTripById = async (tripId: number) => {
  try {
    const response = await apiClient.delete(`/api/v1/trips/${tripId}`);
  } catch (error) {
    console.error(error);
  }
};

// 공개된 여행 일정 목록 조회 - 미완료
export const getPublicTrips = async (
  page: number = 1,
  size: number = 10,
  sort: "copied_count" | "created_at" = "copied_count"
) => {
  try {
    const response = await apiClient.get("/api/v1/trips/public", {
      params: { page, size, sort },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 여행 일정 복사 - 미완료
export const copyTrip = async (tripId: number) => {
  try {
    const response = await apiClient.post(`/api/v1/trips/${tripId}/copy`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 공개/비공개 설정 - 미완료
export const updateTripVisibility = async (
  tripId: number,
  isPublic: boolean
) => {
  try {
    const response = await apiClient.put(`/api/v1/trips/${tripId}/visibility`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
