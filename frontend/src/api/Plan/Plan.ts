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
